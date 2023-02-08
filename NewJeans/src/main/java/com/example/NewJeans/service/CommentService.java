package com.example.NewJeans.service;


import com.example.NewJeans.dto.request.CommentRequestDTO;
import com.example.NewJeans.entity.Board;
import com.example.NewJeans.entity.Comment;
import com.example.NewJeans.entity.Member;
import com.example.NewJeans.repository.BoardRepository;
import com.example.NewJeans.repository.CommentRepository;
import com.example.NewJeans.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //댓글 조회


    //댓글 작성
    public Long create(Long id, CommentRequestDTO requestDTO){

       Board board= boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + id));

        requestDTO.setBoardId(board);

        Comment comment =requestDTO.toEntity();
        commentRepository.save(comment);

        return comment.getCmtID();
    }


    //댓글 삭제

    //댓글 수정





}
