package com.example.NewJeans;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HealthCheckController {

    @GetMapping("/")
    public String check(){
        log.info("Server is running...");
        return "index";
    }

    @GetMapping("/login")
    public String check2(){
        log.info("Server is running...");
        return "login";
    }

    @GetMapping("/register")
    public String check3(){
        log.info("Server is running...");
        return "register";
    }
}
