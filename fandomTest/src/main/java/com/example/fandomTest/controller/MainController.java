package com.example.fandomTest.controller;

import com.example.fandomTest.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final IdolService idolService;

    @RequestMapping(value = "/")
    public String index(Model model){
        log.info("index.do");
        model.addAttribute("idolList", idolService.getIdolList());
        return "index";
    }

}
