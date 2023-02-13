package com.example.fandomTest.controller;

import com.example.fandomTest.dto.response.ListIdolImgResponseDTO;
import com.example.fandomTest.service.IdolImgService;
import com.example.fandomTest.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Positive;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/idolImg")
public class IdolImgController {
    private final IdolImgService idolImgService;

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
        boolean membership = idolImgService.isMemberShip(userId);
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
}
