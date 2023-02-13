package com.example.fandomTest.controller;

import com.example.fandomTest.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {
    private BoardService boardService;

    @GetMapping(value = "/boardList.do")
    public String boardList(){
        log.info("boardList.do");
        return "board/boardList";
    }

    @GetMapping(value = "/boardWrite.do")
    public String boardWrite(){
        log.info("boardWrite.do");
        return "board/boardWrite";
    }


}
