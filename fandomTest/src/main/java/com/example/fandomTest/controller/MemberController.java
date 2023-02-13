package com.example.fandomTest.controller;

import com.example.fandomTest.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/logIn.do")
    public String Login() {
        log.info("logIn.do");
        return "member/login";
    }


    @GetMapping(value = "/register.do")
    public String register() {
        log.info("register.do");
        return "member/register";
    }
}
