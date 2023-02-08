package com.example.NewJeans.Controller;

import com.example.NewJeans.dto.request.BoardCreateRequestDTO;
import com.example.NewJeans.dto.request.CommentRequestDTO;
import com.example.NewJeans.dto.response.BoardDetailResponseDTO;
import com.example.NewJeans.dto.response.CommentResponseDTO;
import com.example.NewJeans.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class CommentController {

    private final CommentService commentService;

    //댓글 등록
    @PostMapping("/{board-id}/{mem-id}/comment")
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
            //CommentResponseDTO commentRequestDTO = commentService.create(id,requestDTO); //memNickName 추가
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

    //댓글 수정

    //댓글 삭제



}
