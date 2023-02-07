package com.example.NewJeans.Controller;


import com.example.NewJeans.dto.request.BoardCreateRequestDTO;
import com.example.NewJeans.dto.request.BoardModifyRequestDTO;
import com.example.NewJeans.dto.response.BoardListResponseDTO;
import com.example.NewJeans.entity.Idol;
import com.example.NewJeans.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //게시글 목록 요청  idolID로 idol별 게시물 페이지 요청
    @GetMapping("/{idol-id}")
    public ResponseEntity<?> retrieveBoardList(
            //@AuthenticationPrincipal Long memId,
            @PathVariable("idol-id") Idol idolId
    )
    {
        log.info("/board/{} Get request!",idolId);

        try {
            BoardListResponseDTO responseDTO = boardService.retrieve(idolId); //memId
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(BoardListResponseDTO.builder().error(e.getMessage()));

        }
    }

    //게시글 등록 요청   파일 업로드 추가 필요
    //@PostMapping("/{idol-id}")
    @PostMapping
    public ResponseEntity<?> createBoard(
            //@AuthenticationPrincipal Long memId,
            // @PathVariable("idol-id") Idol idolId,
            @Validated @RequestBody BoardCreateRequestDTO requestDTO,
            BindingResult result

    )
    {

        if (result.hasErrors()) {
            log.warn("DTO 검증 에러 발생: {}", result.getFieldError());
            return ResponseEntity
                    .badRequest()
                    .body(result.getFieldError());
        }

        try {
            BoardListResponseDTO boardListResponseDTO = boardService.create(requestDTO); //memId,idolId
            return ResponseEntity
                    .ok()
                    .body(boardListResponseDTO);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(BoardListResponseDTO.builder().error(e.getMessage()));
        }

        //return "testBoardList";
        //return null;
    }


    //게시글 삭제 요청  작성자 OR 관리자
    @DeleteMapping("/{board-id}")  //헷갈린다
    public ResponseEntity<?> deleteBoard(
            //@AuthenticationPrincipal Long memId,
            @PathVariable("board-id") Long boardId) {
        log.info("/board/{} DELETE request!", boardId);

        if (boardId == null) {
            return ResponseEntity
                    .badRequest()
                    .body(BoardListResponseDTO.builder().error("boardID를 전달해주세요"));
        }

        try {
            BoardListResponseDTO responseDTO = boardService.delete(boardId); //memId
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(BoardListResponseDTO.builder().error(e.getMessage()));
        }


    }

    //
//
//    //게시글 수정 요청
    @RequestMapping(
            value = "/{board-id}"
            , method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<?> updateBoard(
            //@AuthenticationPrincipal Long memId,
            @PathVariable("board-id") Long boardId,
            @Validated @RequestBody BoardModifyRequestDTO requestDTO, BindingResult result, HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(result.getFieldError());
        }
        log.info("/board/{} {} request", boardId, request.getMethod());
        log.info("modifying dto: {}", requestDTO);

        try {
            BoardListResponseDTO responseDTO = boardService.update(boardId, requestDTO); //memId
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(BoardListResponseDTO.builder().error(e.getMessage()));
        }

    }
}






















