package com.example.NewJeans.controller;


import com.example.NewJeans.dto.response.ListIdolImgResponseDTO;
import com.example.NewJeans.security.TokenProvider;
import com.example.NewJeans.service.IdolImgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/idolImg")
public class IdolImgController {
    private final IdolImgService idolImgService;
    private final TokenProvider tokenProvider;

    //멤버쉽 이미지 보기
    @GetMapping("/idolImg.do")
    public String getImages(
            Model model, Authentication authentication,
            @Positive @RequestParam("idolID") Long idolId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "imgId") String sort){

        Long userId = null;
        if(authentication != null) userId = Long.parseLong((String) authentication.getPrincipal());
        log.info("현재 {}로 시작합니다.", userId);
        boolean membership = idolImgService.isMemberShip(userId, idolId);
        model.addAttribute("memberShip", membership);
        model.addAttribute("idolID", idolId);

        try {
            ListIdolImgResponseDTO listIdolImgResponseDTO = idolImgService.findIdolImgs(idolId,page,size,sort);
            model.addAttribute("idolImgList", listIdolImgResponseDTO);
            return "idol/idolImg";
        } catch (RuntimeException e) {
            log.warn("idolImage GET(리스트) 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","존재하지 않는 아이돌입니다.");
            return "idol/error";
        }
    }

    @GetMapping("/join")
    public String joinMemberShip(
            Model model, HttpServletRequest request,
            @Positive @RequestParam("idol-id") Long idolId,
            @CookieValue(value = "LOGIN_USEREMAIL", required = false) Cookie userEmail
    ){
        log.info("join Get userEmail {}", userEmail.getValue());
        idolImgService.addMemberShip(userEmail.getValue(), idolId);
        return "redirect:/board/" + idolId;
    }

    //쿠키로 저장된 토큰으로부터 유저 정보를 받아오는 메서드
    private Long getTokenSubject(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = "";
        if(cookies != null)
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("ACCESS_TOKEN"))
                    token = cookie.getValue();
            }
        return Long.parseLong(tokenProvider.getSubject(token));
    }
}
