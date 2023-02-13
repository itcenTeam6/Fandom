package com.example.fandomTest.controller;

import com.example.fandomTest.service.IdolImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/idolImg")
public class IdolImgController {
    private IdolImgService idolImgService;

    @GetMapping(value = "/idolImg.do")
    public String idolImg(){
        log.info("idolImg.do");
        return "idol/idolImg";
    }
}
