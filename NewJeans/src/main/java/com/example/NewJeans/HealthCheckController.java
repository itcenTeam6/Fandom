package com.example.NewJeans;

import com.example.NewJeans.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpCookie;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HealthCheckController {


    private final TokenProvider tokenProvider;
    @GetMapping("/")
    public String check(HttpServletRequest httpServletRequest, Model model){

        try{
            Cookie[] cookies = httpServletRequest.getCookies();
            String cookieValue="";

            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if(cookieName.equals("ACCESS_TOKEN")){
                    cookieValue=cookie.getValue();
                }
            }
            boolean validatedToken= tokenProvider.validatedToken(cookieValue);
            System.out.println("validatedToken = " + validatedToken);
            if (validatedToken){
                model.addAttribute("cookieValue","true");
                return "index";
            }else{
                model.addAttribute("cookieValue","false");
                return "index";
            }
        }catch (Exception e){
            model.addAttribute("cookieValue","false");
            return "index";
        }

    }


}
