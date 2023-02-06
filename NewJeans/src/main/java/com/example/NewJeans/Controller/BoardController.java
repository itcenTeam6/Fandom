package com.example.NewJeans.Controller;


import com.example.NewJeans.dto.response.BoardListResponseDTO;
import com.example.NewJeans.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

//    @GetMapping("/test1")
//    public void test(){
//        System.out.println("!!!!");
//    }

    //게시글 등록 요청

//    @PostMapping
//
//
//    //게시글 삭제 요청
//    @DeleteMapping("/{idolID}/{memid}")
//
//    //게시글 수정 요청
//    @RequestMapping(
//            value = "/{idolID}/{memid}"
//            ,method = {RequestMethod.PUT,RequestMethod.PATCH}
//    )
//
//
    //게시글 목록 요청  idolID를
    @GetMapping("/{idol}")
    public ResponseEntity<?> retrieveBoardList(

            @AuthenticationPrincipal Long memID,
            Long idolID
    )
    {
        log.info("/board Get request");

        try {
            BoardListResponseDTO responseDTO = boardService.retrieve(memID, idolID);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(BoardListResponseDTO.builder().error(e.getMessage()));

        }
    }

















}
