package com.example.NewJeans.service;


import com.example.NewJeans.dto.request.BoardCreateRequestDTO;
import com.example.NewJeans.dto.request.BoardModifyRequestDTO;
import com.example.NewJeans.dto.response.BoardDetailResponseDTO;
import com.example.NewJeans.dto.response.BoardListResponseDTO;
import com.example.NewJeans.entity.Board;
import com.example.NewJeans.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    //게시판 목록 조회  페이징 처리 필요
    @Transactional
    public BoardListResponseDTO retrieve(Long memId, Long idolId){
         Optional<Board> entityList= boardRepository.findByIdolId(idolId); //아이돌 번호에 맞는 게시판 목록 불러옴

        List<BoardDetailResponseDTO> dtoList = entityList.stream()
                .map(BoardDetailResponseDTO::new)
                .collect(Collectors.toList());

        return BoardListResponseDTO.builder()
                .boards(dtoList)
                .build();

    }

    //게시물 등록
    @Transactional
    public BoardListResponseDTO create(final BoardCreateRequestDTO createRequestDTO) //final Long memId,final Long idolId)
        throws RuntimeException
    {
        Board board=createRequestDTO.toEntity();
        boardRepository.save(board);
        log.info("게시물이 등록되었습니다. 내용:{} 파일:{}",createRequestDTO.getBoardContent(),createRequestDTO.getBoardFile());

        //return retrieve(memID,idolID);
        return null;
    }

    //게시물 삭제




    //게시물 수정
    public BoardListResponseDTO update(
            final Long boardId, final BoardModifyRequestDTO requestDTO, final Long memId){

        Optional<Board> targetEntity=boardRepository.findById(boardId);

        targetEntity.ifPresent(entity->{
            entity.setBoardContent(requestDTO.getBoardContent());
            entity.setBoardFile(requestDTO.getBoardFile());
            boardRepository.save(entity);
        });

        return null;

    }


    }















