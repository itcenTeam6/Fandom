package com.example.fandomTest.controller;

import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.service.BoardService;
import com.example.fandomTest.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            Model model
    ){
        log.info("boardList.do - idolID is {}", idolID);
        Idol idol = idolService.getIdol(idolID);
        model.addAttribute("idol", idol);
        return "board/boardList";
    }

    @GetMapping(value = "/boardWrite.do")
    public String boardWrite(
            @RequestParam(value = "idolID") Long idolID,
            Model model
    ){
        log.info("boardWrite.do - idolID is {}", idolID);
        model.addAttribute("idol", idolService.getIdol(idolID));
        return "board/boardWrite";
    }
}
