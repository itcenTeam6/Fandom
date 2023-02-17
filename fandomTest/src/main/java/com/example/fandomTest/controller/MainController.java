package com.example.fandomTest.controller;

import com.example.fandomTest.security.TokenProvider;
import com.example.fandomTest.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final IdolService idolService;
    private final TokenProvider tokenProvider;

    @RequestMapping(value = "/")
    public String index(
            HttpServletRequest httpServletRequest,
            Model model
    ){
        log.info("index.do");
        
        // idol 이미지 처리
        model.addAttribute("idolList", idolService.getIdolList());
        
        // 로그인 유무 처리
        Cookie[] cookies = httpServletRequest.getCookies();
        String cookieValue="";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals("ACCESS_TOKEN")) {
                    cookieValue = cookie.getValue();
                }
            }
        }

        try{
            boolean validatedToken= tokenProvider.validatedToken(cookieValue);
            if (validatedToken){
                model.addAttribute("cookieValue","true");
            }
        }catch (Exception e){
            model.addAttribute("cookieValue","false");
        }

        return "index";
    }

}
