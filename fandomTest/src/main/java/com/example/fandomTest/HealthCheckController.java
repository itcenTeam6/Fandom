package com.example.fandomTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HealthCheckController {
    @GetMapping(value = "/")
    public String check(){
        log.info("Server is running...");
        return "index";
    }
}
