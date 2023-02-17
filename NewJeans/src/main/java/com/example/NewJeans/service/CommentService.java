package com.example.NewJeans.service;


import com.example.NewJeans.Entity.Member;
import com.example.NewJeans.dto.response.CommentSaveResponseDTO;
import com.example.NewJeans.dto.request.CommentRequestDTO;
import com.example.NewJeans.Entity.Board;
import com.example.NewJeans.Entity.Comment;
import com.example.NewJeans.repository.BoardRepository;
import com.example.NewJeans.repository.CommentRepository;
import com.example.NewJeans.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //댓글 조회

    public List<Comment> retrieve(Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. " + boardId));

        List<Comment> comments = board.getComments();
        return comments;
        //return comments.stream().map(CommentResponseDTO::new).collect(Collectors.toList());

    }


    //댓글 작성
    public void create(Long boardId,CommentRequestDTO requestDTO) {

        //Board board=new Board();
        //board.setBoardID(boardId);


        //Long boardId = boardID.getBoardID();

        Board board = boardRepository.findById(boardId).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + boardId));

        requestDTO.setBoardId(board);
        Comment comment = requestDTO.toEntity();
        commentRepository.save(comment);

    }

    //댓글 삭제
    public void delete(Long cmtId) {
        Comment comment = commentRepository.findById(cmtId).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + cmtId));
        commentRepository.delete(comment);
    }

    //댓글 수정
    public void update(Long cmtId, CommentRequestDTO requestDTO) {
        Comment comment = commentRepository.findById(cmtId).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + cmtId));

        comment.update(requestDTO.getCmtContent());
    }

    public CommentSaveResponseDTO commentSave(
            final Long boardId,
            final String userEmail,
            final String commentText
    ) {
        Member byMemEmail = memberRepository.findByMemEmail(userEmail);
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("게시글 없음"));

        Comment comment = Comment.builder()
                .boardId(board)
                .memId(byMemEmail)
                .cmtContent(commentText)
                .build();

        Comment save = commentRepository.save(comment);

        return CommentSaveResponseDTO.builder()
                .userNickName(byMemEmail.getMemNickname())
                .commentText(commentText)
                .savedCmtID(save.getCmtID())
                .userEmail(save.getMemId().getMemEmail())
                .build();
    }
}