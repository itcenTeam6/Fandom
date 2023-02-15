package com.example.NewJeans.service;


import com.example.NewJeans.Entity.Board;
import com.example.NewJeans.Entity.Idol;
import com.example.NewJeans.Entity.Member;
import com.example.NewJeans.dto.request.CreateBoardRequestDTO;
import com.example.NewJeans.dto.request.ModifyBoardRequestDTO;
import com.example.NewJeans.dto.response.DetailBoardResponseDTO;
import com.example.NewJeans.dto.response.DetailBoardUpdateResponseDTO;
import com.example.NewJeans.dto.response.ListBoardResponseDTO;
import com.example.NewJeans.repository.BoardRepository;
import com.example.NewJeans.repository.IdolRepository;
import com.example.NewJeans.repository.MemberRepository;
import com.example.NewJeans.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
//    private static final String IMAGE_PATH = "E:\\image";
    @Value("${file.dir}")
    private String IMAGE_PATH;

    //게시판 목록 조회  페이징 처리 필요
    @Transactional
    public ListBoardResponseDTO retrieve(Long idolId, Pageable pageable) {

        //페이징 처리
        Page<Board> listBoards = boardRepository.findByIdolId(idolId, pageable);

        List<DetailBoardResponseDTO> dtoList = listBoards.stream()
                .map(DetailBoardResponseDTO::new)
                .collect(Collectors.toList());

        for (DetailBoardResponseDTO detailBoardResponseDTO : dtoList) {

            String fileName = detailBoardResponseDTO.getBoardFile();

            log.info("/loadFile GET - {}", fileName);

            File f = new File(IMAGE_PATH + fileName);

            if (!f.exists()) {
                return null;
            }

            try (FileInputStream fis = new FileInputStream(f)) {

                // 파일명을 원래대로 복구
                fileName = fileName.substring(fileName.lastIndexOf("_") + 1);

                // 파일명이 한글인 경우 인코딩 재설정
                String encoding = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

                // 4. 파일 순수데이터 바이트배열에 저장.
                byte[] rawData = IOUtils.toByteArray(fis);

                //String으로 변환
                byte[] encode = Base64.encode(rawData);
                String s = new String(encode, "UTF-8");

                detailBoardResponseDTO.setBoardFilePath(s);
                String localDateTime = detailBoardResponseDTO.getBoardDate();
                String boardDateDay = localDateTime.substring(0, 10);
                String boardDateTime = localDateTime.substring(11, 19);
                String boardDate = boardDateDay + " " + boardDateTime;
//                log.info("localDateTime:{}", localDateTime);
//                log.info("boardDateDay:{}", boardDateDay);
//                log.info("boardDateTime:{}", boardDateTime);
//                log.info("boardDate:{}", boardDate);
                detailBoardResponseDTO.setBoardDate(boardDate);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return ListBoardResponseDTO.builder()
                .boards(dtoList)
                .build();
    }

    //게시물 등록
    public Long create(
            final CreateBoardRequestDTO createRequestDTO,
            final Long idolId,
            MultipartFile fileList,
            Long userId, String userNickName, boolean fileExist) //Long userId,
            throws RuntimeException, IOException {
        //파일 -> 경로
        String boardFile = FileUtils.uploadFile(fileList, IMAGE_PATH);
        log.info("boardFile:{}", boardFile);
        createRequestDTO.setBoardFile(boardFile);

        Idol idol = new Idol();
        idol.setIdolID(idolId);

        Member member = new Member();
        member.setMemID(userId);

        Board board = createRequestDTO.toEntity();
        board.setBoardFile(board.getBoardFile());
        board.setIdolID(idol);
        board.setIdol(idolId);
        board.setMember(member);
        board.setMemNickName(userNickName); //작성자 닉네임
        board.setFileExist(fileExist);

        boardRepository.save(board);
        log.info("게시물이 등록되었습니다. 내용:{} 파일:{}", createRequestDTO.getBoardContent(), createRequestDTO.getBoardFile());
        return idolId;
    }

    //게시물 삭제
    public void delete(final Long boardId, final Long idolId) {
        try {
            boardRepository.deleteById(boardId);
        } catch (Exception e) {
            log.error("board-id가 존재하지 않아 삭제에 실패했습니다.-boardId:{}, err: {}",
                    boardId, e.getMessage());
            throw new RuntimeException("boardId가 존재하지 않아 삭제에 실패했습니다.");
        }
    }

    //게시물 수정
    public void update(
            final Long boardId,
            final String boardContent,
            final MultipartFile fileList,
            final boolean fileExist
    ) {
        String updateBoardFile = FileUtils.uploadFile(fileList, IMAGE_PATH);
        log.info("updateBoardFile:{}", updateBoardFile);

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("게시글 없음"));

        board.setBoardContent(boardContent);
        board.setBoardFile(updateBoardFile);
        board.setFileExist(fileExist);
        boardRepository.save(board);
        log.info("게시물이 수정되었습니다. 내용:{} 파일:{}", board.getBoardContent(), board.getBoardFile());

//        targetEntity.ifPresent(entity -> {
//            entity.setBoardContent(requestDTO.getBoardContent());
//            entity.setBoardFile(requestDTO.getBoardFile());
//            boardRepository.save(entity);
//        });
    }
    
    public DetailBoardUpdateResponseDTO boardUpdate(final Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("BoardUpdate 게시글이 없음"));
        DetailBoardUpdateResponseDTO dtoBuilder = DetailBoardUpdateResponseDTO.builder()
                .boardId(board.getBoardID())
                .member(board.getMember())
                .boardContent(board.getBoardContent())
                .boardFile(board.getBoardFile())
                .fileExist(board.isFileExist())
                .build();

        if (dtoBuilder.isFileExist()){
            String fileName = board.getBoardFile();
            log.info("/loadFile GET - {}", fileName);

            File f = new File(IMAGE_PATH + fileName);
            if (!f.exists()) {
                return null;
            }

            try (FileInputStream fis = new FileInputStream(f)) {
                fileName = fileName.substring(fileName.lastIndexOf("_") + 1);
                String encoding = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

                byte[] rawData = IOUtils.toByteArray(fis);
                byte[] encode = Base64.encode(rawData);
                String s = new String(encode, "UTF-8");

                dtoBuilder.setBoardFilePath(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return dtoBuilder;
    }
}

