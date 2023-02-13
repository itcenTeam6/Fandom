package com.example.NewJeans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testRedirectController {

    @GetMapping(value = "/memberLogin.do")
    public String memberLogin() {
        return "member/login";
    }

    @GetMapping(value = "/boardList.do")
    public String boardList() {
        return "board/boardList";
    }

    @GetMapping(value = "/boardWrite.do")
    public String boardWrite() {
        return "board/boardWrite";
    }

    @GetMapping(value = "/idolImg.do")
    public String idolImg() {
        return "idol/idolImg";
    }
}
