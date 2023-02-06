package com.example.NewJeans.service;


import com.example.NewJeans.dto.request.BoardCreateRequestDTO;
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

    //게시판 목록 조회
    @Transactional
    public BoardListResponseDTO retrieve(Long memID, Long idolID){
         Optional<Board> entityList= boardRepository.findByIdolId(idolID);

        List<BoardDetailResponseDTO> dtoList = entityList.stream()
                .map(BoardDetailResponseDTO::new)
                .collect(Collectors.toList());

        return BoardListResponseDTO.builder()
                .boards(dtoList)
                .build();

    }


    //게시물 등록
    public BoardListResponseDTO create(
            final BoardCreateRequestDTO createRequestDTO, final Long memID,final Long idolID)
        throws RuntimeException
    {
        Board board=createRequestDTO.toEntity();
        board.setBoardID(memID);

        boardRepository.save(board);
        log.info("게시물이 등록되었습니다. 내용:{}",createRequestDTO.getBoardContent());
        return retrieve(memID,idolID);
    }

    //게시물 수정
//    public BoardListResponseDTO update(
//            final Long boardId, final BoardModifyRequestDTO modifyRequestDTO,final String memId){
//
//        Optional<Board> targetEntity=boardRepository.findById(boardId);
//
//        targetEntity.ifPresent(entity->{
//            entity.setBoardContent();
//        });
//
//
//    }


    }




    //게시물 삭제










