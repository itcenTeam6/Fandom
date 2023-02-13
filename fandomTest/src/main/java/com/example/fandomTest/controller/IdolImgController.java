package com.example.fandomTest.controller;

import com.example.fandomTest.entity.IdolImg;
import com.example.fandomTest.service.IdolImgService;
import com.example.fandomTest.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/idolImg")
public class IdolImgController {
    private final IdolImgService idolImgService;

    private final IdolService idolService;

    @GetMapping(value = "/idolImg.do")
    public String idolImg(
            @RequestParam(value = "idolID") Long idolID,
            Model model
    ){
        log.info("idolImg.do - idolID is {}", idolID);

        List<IdolImg> idolImgList = idolImgService.selectIdolImg(idolID);
        model.addAttribute("idolImgList", idolImgList);
        model.addAttribute("idol", idolService.getIdol(idolID));
        return "idol/idolImg";
    }
}
