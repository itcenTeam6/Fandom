package com.example.fandomTest.controller;

import com.example.fandomTest.dto.request.FileRequestDTO;
import com.example.fandomTest.dto.request.PostRequestDTO;
import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.service.BoardService;
import com.example.fandomTest.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {
    private final BoardService boardService;
    private final IdolService idolService;

    @GetMapping(value = "/boardList.do")
    public String boardList(
            @RequestParam(value = "idolID") Long idolID,
            @CookieValue(value = "ACCESS_TOKEN", required = false) Cookie cookieValue,
            Model model
    ) {
        if (cookieValue == null) {
            return "redirect:/";
        }

        log.info("boardList.do - idolID is {}", idolID);
        log.info("cookieValue is {}", cookieValue.);

        Idol idol = idolService.getIdol(idolID);
        model.addAttribute("idolID", idolID);
        model.addAttribute("idol", idol);
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
            PostRequestDTO postRequestDTO,
            Model model
    ) throws IOException {
        log.info("boardWrite.do POST - idolID is {}", postRequestDTO.getIdolID());
        model.addAttribute("idolID", postRequestDTO.getIdolID());
        
        // 파일 저장
        if (!postRequestDTO.getInputImg().isEmpty()) {
            FileRequestDTO fileRequestDTO = new FileRequestDTO(postRequestDTO.getInputImg());
            log.info(fileRequestDTO.getSavePath());
            postRequestDTO.getInputImg().transferTo(new File(fileRequestDTO.getSavePath())); // 실제 로컬에 파일 저장
            log.info("file saved");
        }

        return "board/boardWrite";
    }
}
