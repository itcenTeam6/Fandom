package com.example.NewJeans.Controller;

import com.example.NewJeans.dto.request.CommentRequestDTO;
import com.example.NewJeans.dto.response.ListBoardResponseDTO;
import com.example.NewJeans.dto.response.CommentResponseDTO;
import com.example.NewJeans.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class CommentController {

    private final CommentService commentService;

    //댓글 등록
    @PostMapping("/{board-id}/{mem-id}/comments")
    public String createComment(
            Model model,
            //@AuthenticationPrincipal Long memId,
            @PathVariable("board-id") Long boardId,
            @PathVariable("mem-id") Long id,
            @Validated @RequestBody CommentRequestDTO requestDTO,
            BindingResult result

    ) {
        if (result.hasErrors()) {
            log.warn("DTO 검증 에러 발생: {}", result.getFieldError());
            model.addAttribute("error", "createComment 에러");
            return null; //페이지 만들어서 보내
        }

        try {
            //CommentResponseDTO commentResponseDTO = commentService.create(boardId,requestDTO); //memNickName 추가
            //model.addAttribute("commentRequestDTO", commentRequestDTO);
            return "list";

//            return ResponseEntity
//                    .ok()
//                    .body(boardDetailResponseDTO);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            model.addAttribute("error", "createBoard 에러");
            return null; //페이지 만들어서 보내
//            return ResponseEntity
//                    .internalServerError()
//                    .body(BoardListResponseDTO.builder().error(e.getMessage()));
        }
    }


    //댓글 보기
    @GetMapping("{idol-id}/{board-id}/comments")
    public String retrieveCommentList(
            Model model,
            @PathVariable("board-id") Long boardId
    )
    {
        List<CommentResponseDTO> commentResponseDTO = commentService.retrieve(boardId);
        model.addAttribute("CommentResponseDTO",commentResponseDTO);
        return null;
    }



    //댓글 삭제
    @DeleteMapping("/{idol-id}/{board-id}/comments/{comment-id}")
    public ResponseEntity<?> deleteComment(
            @PathVariable("board-id") Long boardId,
            @PathVariable("comment-id") Long cmtId
    )
    {
        log.info("/board/{}/comments/{} DELETE request!", boardId,cmtId);

        if (boardId == null) {
            return ResponseEntity
                    .badRequest()
                    .body(ListBoardResponseDTO.builder().error("boardID를 전달해주세요"));
        }

        try {
            commentService.delete(cmtId); //memId
            return null; //게시판으로
           // return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ListBoardResponseDTO.builder().error(e.getMessage()));
        }
    }


    //댓글 수정
    @RequestMapping(
            value = "/{board-id}/comments/{comment-id}"
            , method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> updateComment(
            //@AuthenticationPrincipal Long memId,
            @PathVariable("board-id") Long boardId,
            @PathVariable("comment-id") Long cmtId,
            @Validated @RequestBody CommentRequestDTO requestDTO, BindingResult result, HttpServletRequest request
    )
    {
        commentService.update(cmtId,requestDTO);
        return null;

    }






}
