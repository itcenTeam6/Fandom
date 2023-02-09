package com.example.NewJeans.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class testRedirectController {

    @GetMapping(value = "/boardList.do")
    public String boardList(){ return  "board/boardList";}

    @GetMapping(value = "/boardWrite.do")
    public String boardWrite(){ return "board/boardWrite";}

    @GetMapping(value = "/idolImg.do")
    public String idolImg() { return "idol/idolImg";}
}
