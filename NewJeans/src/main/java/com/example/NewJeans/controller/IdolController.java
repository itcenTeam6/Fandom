package com.example.NewJeans.controller;

import com.example.NewJeans.dto.request.CreateIdolRequestDTO;
import com.example.NewJeans.dto.request.ModifyIdolRequestDTO;
import com.example.NewJeans.dto.response.DetailIdolResponseDTO;
import com.example.NewJeans.dto.response.ListIdolResponseDTO;
import com.example.NewJeans.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/idol")
public class IdolController {
    private final IdolService idolService;

    // 아이돌 저장
    @PostMapping
    public String postIdol(Model model,
                           @Validated @RequestBody CreateIdolRequestDTO createIdolRequestDTO,
                           BindingResult result){
        if(result.hasErrors()){
            log.warn("createIdol 핸들러 메서드 에러 발생 : {}", result.getFieldError());
            model.addAttribute("errorMessage","postIdol에러");
            return "Idol/error";
        }

        try {
            DetailIdolResponseDTO detailIdolResponseDTO = idolService.create(createIdolRequestDTO);
            model.addAttribute("detailIdolResponseDTO", detailIdolResponseDTO);
            return "Idol/IdolDetail";
        } catch (RuntimeException e) {
            log.warn("idol 저장 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","postIdol에러");
            return "Idol/error";
        }
    }

    // 아이돌 목록 조회
    @GetMapping
    public String getIdols(Model model,
                           @RequestParam(name = "page", required = false, defaultValue = "1")int page,
                           @RequestParam(name = "size", required = false, defaultValue = "10")int size,
                           @RequestParam(name = "sort", required = false, defaultValue = "idolID")String sort){
        try {
            ListIdolResponseDTO listIdolResponseDTO = idolService.findIdols(page, size, sort);
            model.addAttribute("listIdolResponseDTO",listIdolResponseDTO);
            return "Idol/Idol";
        } catch (RuntimeException e) {
            log.warn("idol 목록 조회 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","getIdols 에러");
            return "Idol/error";
        }
    }

    // 아이돌 상세 조회
    @GetMapping("/{idol-id}")
    public String getIdol(Model model, @Positive @PathVariable(value = "idol-id") Long idolId){

        try {
            DetailIdolResponseDTO detailIdolResponseDTO = idolService.findIdol(idolId);
            model.addAttribute("detailIdolResponseDTO",detailIdolResponseDTO);
            return "Idol/IdolDetail";
        } catch (RuntimeException e) {
            log.warn("아이돌 상세 조회 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","getIdol 에러");
            return "Idol/error";
        }
    }

    // 아이돌 수정
    @RequestMapping(value = "/{idol-id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public String patchIdol(Model model,
                            @Validated @RequestBody ModifyIdolRequestDTO modifyIdolRequestDTO,
                            @Positive @PathVariable(value = "idol-id") Long idolId,
                            BindingResult result){

        try {
            DetailIdolResponseDTO detailIdolResponseDTO = idolService.updateIdol(idolId, modifyIdolRequestDTO);
            model.addAttribute("detailIdolResponseDTO",detailIdolResponseDTO);
            return "Idol/IdolDetail";
        } catch (RuntimeException e) {
            log.warn("아이돌 수정 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","patchIdol 에러");
            return "Idol/error";
        }
    }

    // 아이돌 삭제
    @DeleteMapping("/{idol-id}")
    public String deleteIdol(Model model, @Positive @PathVariable(value = "idol-id") Long idolId){

        try {
            idolService.removeIdol(idolId);
            return "Idol/Idol";
        } catch (RuntimeException e) {
            log.warn("아이돌 삭제 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","deleteIdol 에러");
            return "Idol/error";
        }
    }
}
