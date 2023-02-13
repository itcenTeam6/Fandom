package com.example.NewJeans.service;


import com.example.NewJeans.Entity.Member;
import com.example.NewJeans.dto.request.CreateBoardRequestDTO;
import com.example.NewJeans.dto.request.ModifyBoardRequestDTO;
import com.example.NewJeans.dto.response.DetailBoardResponseDTO;
import com.example.NewJeans.dto.response.ListBoardResponseDTO;
import com.example.NewJeans.Entity.Board;
import com.example.NewJeans.Entity.Idol;
import com.example.NewJeans.repository.BoardRepository;
import com.example.NewJeans.repository.IdolRepository;
import com.example.NewJeans.repository.MemberRepository;
import com.example.NewJeans.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final IdolRepository idolRepository;
    private final MemberRepository memberRepository;
    private static final String IMAGE_PATH="E:\\image";


    //게시판 목록 조회  페이징 처리 필요
    @Transactional
    public ListBoardResponseDTO retrieve(Long idolId, Pageable pageable){ //Long memId,,int page, int size, String sort

        Page<Board> listBoards = boardRepository.findByIdolId(idolId,pageable);

        //페이징처리
        //Page<Board> pageBoards = boardRepository.findAll(PageRequest.of(page - 1, size,Sort.by(sort).descending()));
        //List<Board> listBoards= pageBoards.getContent();

        List<DetailBoardResponseDTO> dtoList = listBoards.stream()
                .map(DetailBoardResponseDTO::new)
                .collect(Collectors.toList());


        return ListBoardResponseDTO.builder()
                .boards(dtoList)
                .build();

    }

    //게시물 등록
    public Long create(
            final CreateBoardRequestDTO createRequestDTO, final Long idolId, MultipartFile file) //Long userId,
            throws RuntimeException, IOException {
        //파일 -> 경로
        //String boardFile= FileUtils.uploadFile(createRequestDTO.getBoardImg(),IMAGE_PATH);
        //createRequestDTO.setBoardFile(boardFile);
        String boardFile=System.getProperty("user.dir")+"/src/main/resources/static/files";
        UUID uuid=UUID.randomUUID();
        String filename=uuid+"_"+file.getOriginalFilename();
        File saveFile=new File(boardFile,filename);
        file.transferTo(saveFile);
        Board board=createRequestDTO.toEntity();
        //board.setFilePath(filename);
        board.setBoardFile("/files/"+filename);



        Idol idol=new Idol();
        idol.setIdolID(idolId);

//        Member member=new Member();
//        member.setMemID(userId);

        //board.setBoardFile(board.getBoardFile());
        board.setIdolID(idol);
        board.setIdol(idolId);
        //board.setMember(member);
        board.setMemNickName(board.getMemNickName()); //작성자 닉네임
        boardRepository.save(board);
        log.info("게시물이 등록되었습니다. 내용:{} 파일:{}",createRequestDTO.getBoardContent(),createRequestDTO.getBoardFile());

        return idolId;

    }

    //게시물 삭제
    public void delete(final Long boardId, final Long idolId){ //final Long memId,
        try {
            boardRepository.deleteById(boardId);
        } catch (Exception e) {
            log.error("board-id가 존재하지 않아 삭제에 실패했습니다.-boardId:{}, err: {}",
                    boardId,e.getMessage());
            throw new RuntimeException("boardId가 존재하지 않아 삭제에 실패했습니다.");
        }

    }
    //
//
    //게시물 수정
    public void update(
            final Long boardId,final Long idolId, final ModifyBoardRequestDTO requestDTO){ //final Long memId,

        Optional<Board> targetEntity=boardRepository.findById(boardId);

        targetEntity.ifPresent(entity->{
            entity.setBoardContent(requestDTO.getBoardContent());
            entity.setBoardFile(requestDTO.getBoardFile());
            entity.setMemNickName(requestDTO.getMemNickName());
            boardRepository.save(entity);
        });



    }



}

