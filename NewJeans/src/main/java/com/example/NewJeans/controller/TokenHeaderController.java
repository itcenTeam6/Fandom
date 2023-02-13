package com.example.NewJeans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TokenHeaderController {

    @GetMapping("/test")
    public String test(){
        return "member/method";
    }

    @GetMapping("/tokenverification")
    public @ResponseBody String dd(){
        return "success";
    }

}
