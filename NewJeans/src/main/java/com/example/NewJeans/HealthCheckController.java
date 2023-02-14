package com.example.NewJeans;

import com.example.NewJeans.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.HttpCookie;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HealthCheckController {


    private final TokenProvider tokenProvider;
    @GetMapping("/")
    public String check(HttpServletRequest httpServletRequest, Model model){
        Cookie[] cookies = httpServletRequest.getCookies();
        String cookieValue="";

        if(cookies != null)
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if(cookieName.equals("ACCESS_TOKEN")){
                    cookieValue=cookie.getValue();
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



        log.info("Server is running...");
        return "index";
    }
}
