package com.example.fandomTest.controller;

import com.example.fandomTest.dto.request.FileRequestDTO;
import com.example.fandomTest.dto.request.PostRequestDTO;
import com.example.fandomTest.dto.request.PostSaveRequestDTO;
import com.example.fandomTest.dto.response.BoardResponseDTO;
import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.service.BoardService;
import com.example.fandomTest.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.constraints.Positive;
import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {
    private final BoardService boardService;
    private final IdolService idolService;
    @Value("${file.dir}")
    private String fileDir;

    @GetMapping(value = "/boardList.do")
    public String boardList(
            @Positive @RequestParam(value = "idolID") Long idolID,
            @CookieValue(value = "LOGIN_USEREMAIL", required = false) Cookie userEmail,
            @CookieValue(value = "LOGIN_USERNICK", required = false) Cookie userNick,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "8") int size,
            Model model
    ) {
        if (userEmail == null) {
            return "redirect:/";
        }

        log.info("boardList.do - idolID is {}", idolID);
        log.info("cookieValue is {}", cookieValue);


        model.addAttribute("idolID", idolID);

//        try {
//            boardService.findBoardLists(idolID, page, size, sort);
//        }catch (RuntimeException e){
//            log.warn("boardList GET 에러 : {}", e.getMessage());
//        }

        Idol idol = idolService.getIdol(idolID);

        BoardResponseDTO boardDTO = BoardResponseDTO.builder()
                .idol(idol)
                .userEmail(userEmail.getValue())
                .userNick(userNick.getValue())
                .build();

        model.addAttribute("boardDTO", boardDTO);
        return "board/boardList";
    }

    @GetMapping(value = "/boardWrite.do")
    public String boardWrite(
            @RequestParam(value = "idolID") Long idolID,
            Model model
    ) {
        log.info("boardWrite.do GET- idolID is {}", idolID);
        model.addAttribute("idolID", idolID);
        return "board/boardWrite";
    }

    @PostMapping(value = "/boardWrite.do")
    public String boardPost(
            final PostRequestDTO postRequestDTO,
            @CookieValue(value = "LOGIN_USEREMAIL", required = false) Cookie userEmail,
            Model model
    ) throws IOException {
        log.info("boardWrite.do POST - idolID is {}", postRequestDTO.getIdolID());
        log.info("userEmail is {}", userEmail.getValue());

        // 파일 저장
        if (!postRequestDTO.getInputImg().isEmpty()) {
            FileRequestDTO fileRequestDTO = new FileRequestDTO(postRequestDTO.getInputImg(), fileDir);
            postRequestDTO.getInputImg().transferTo(new File(fileRequestDTO.getSavePath())); // 실제 로컬에 파일 저장
            log.info("file saved - {}", fileRequestDTO.getSavePath());

            PostSaveRequestDTO postSaveRequestDTO = PostSaveRequestDTO.builder()
                    .filePath(fileRequestDTO.getSavePath())
                    .content(postRequestDTO.getInputTxt())
                    .idolId(postRequestDTO.getIdolID())
                    .userEmail(userEmail.getValue())
                    .build();

            boardService.create(postSaveRequestDTO);
        }else {
            PostSaveRequestDTO postSaveRequestDTO = PostSaveRequestDTO.builder()
                    .content(postRequestDTO.getInputTxt())
                    .idolId(postRequestDTO.getIdolID())
                    .userEmail(userEmail.getValue())
                    .build();

            boardService.create(postSaveRequestDTO);
        }

        model.addAttribute("idolID", postRequestDTO.getIdolID());

//        return "redirect:/board/boardList.do";
        return "board/boardWrite";
    }
}
