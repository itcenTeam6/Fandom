package com.example.NewJeans.controller;


import com.example.NewJeans.dto.request.CreateIdolImgRequestDTO;
import com.example.NewJeans.dto.request.ModifyIdolImgRequestDTO;
import com.example.NewJeans.dto.response.DetailIdolImgResponseDTO;
import com.example.NewJeans.dto.response.ListIdolImgResponseDTO;
import com.example.NewJeans.security.TokenProvider;
import com.example.NewJeans.service.IdolImgService;
import com.example.NewJeans.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/membership")
public class IdolImgController {
    private final IdolImgService idolImgService;
    private final TokenProvider tokenProvider;
    private static final String UPLOAD_PATH = "E:\\img";

    @PostMapping("/upload-form")
    public String uploadForm(Model model,
                             @Validated @RequestBody CreateIdolImgRequestDTO createIdolImgRequestDTO){

//        // 관리자가 아니라면 업로드 불가능
//        Long userId = null;
//        if(authentication != null) userId = Long.parseLong((String) authentication.getPrincipal());
//        if(!idolImgService.isAdmin(userId)){
//            log.warn("어드민이 아닌 회원은 업로드 할 수 없습니다.");
//            model.addAttribute("errorMessage","관리자 권한이 필요합니다.");
//            return "Idol/error";
//        }


        // 이미지 데이터 데이터 베이스에 저장
        try {
            DetailIdolImgResponseDTO detailIdolImgResponseDTO = idolImgService.create(createIdolImgRequestDTO);
            model.addAttribute("imgId", detailIdolImgResponseDTO.getImgId());
            return "Idol/IdolImage-upload";
        } catch (RuntimeException e) {
            log.warn("idolImage POST 에러 : {}",e.getMessage());
            return "Idol/error";
        }
    }

    @PostMapping("/upload")
    public String postImage(@RequestParam("file") List<MultipartFile> fileList,
                            @RequestParam String imgId){
        log.info("/upload POST! - {}", fileList);

        String imgPath = null;

        for (MultipartFile file: fileList) {
            log.info("file-name: {}", file.getName());
            log.info("file-origin-name: {}", file.getOriginalFilename());
            log.info("file-size: {}KB", (double) file.getSize() / 1024);
            log.info("file-type: {}", file.getContentType());
            System.out.println("==================================================================");

            imgPath = FileUtils.uploadFile(file, UPLOAD_PATH);
        }

        Long imageId = Long.parseLong(imgId);

        try {
            idolImgService.changePath(imageId,imgPath);
        } catch (RuntimeException e) {
            return "Idol/error";
        }

        return "redirect:/membership/upload-form";
    }


    //멤버쉽 이미지 보기
    @GetMapping("/{idol-id}")
    public String getImages(Model model, HttpServletRequest request,
                            @Positive @PathVariable("idol-id") Long idolId,
                            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
                            @RequestParam(name = "sort", required = false, defaultValue = "imgId") String sort){

//        Long userId = null;
//        if(authentication != null) userId = Long.parseLong((String) authentication.getPrincipal());
//        log.info("현재 {}로 시작합니다.", userId);
//        boolean membership = idolImgService.isMemberShip(userId);
//        model.addAttribute("memberShip",membership);

        Long memId = getTokenSubject(request); //쿠키 -> 토큰 -> 유저아이디
        boolean membership = idolImgService.isMemberShip(memId,idolId); // 유저 아이디 + 아이돌 아이디 -> 멤버쉽 여부

        model.addAttribute("memberShip",membership); //멤버쉽 여부
        model.addAttribute("idolId", idolId); //아이돌 아이디

        try {
            ListIdolImgResponseDTO listIdolImgResponseDTO = idolImgService.findIdolImgs(idolId,page,size,sort);
            model.addAttribute("listIdolImgResponseDTO",listIdolImgResponseDTO);
            return "Idol/IdolImage";
        } catch (RuntimeException e) {
            log.warn("idolImage GET(리스트) 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","존재하지 않는 아이돌입니다.");
            return "Idol/error";
        }
    }

    // 멤버쉽 이미지 상세 보기
    @GetMapping("/{idol-id}/{image-id}")
    public String getImage(Model model, Authentication authentication,
                           @Positive @PathVariable("idol-name") Long idolId,
                           @Positive @PathVariable("image-id") Long imageId){
        log.info("이미지 상세 보기 실행");


        try {
            DetailIdolImgResponseDTO detailIdolImgResponseDTO = idolImgService.findIdolImg(idolId, imageId);
            model.addAttribute("detailIdolImgResponseDTO",detailIdolImgResponseDTO);
            return "Idol/IdolImgDetail";
        } catch (RuntimeException e) {
            log.warn("idolImage GET(상세) 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage", "getImage 에러");
            return "Idol/error";
        }
    }

    // 관리자가 이미지 수정
    @RequestMapping(value="/{image-id}",method = {RequestMethod.PUT,RequestMethod.PATCH})
    public String patchImage(Model model,
                              @Positive @PathVariable("image-id") Long imageId,
                              @Validated @RequestBody ModifyIdolImgRequestDTO modifyIdolImgRequestDTO){

//        Long userId = null;
//        if(authentication != null) userId = Long.parseLong((String)authentication.getPrincipal());
//        if(!idolImgService.isAdmin(userId)){
//            log.warn("어드민이 아닌 회원은 수정 할 수 없습니다.");
//            model.addAttribute("errorMessage","관리자 권한이 필요합니다.");
//            return "Idol/error";
//        }

        try {
            DetailIdolImgResponseDTO detailIdolImgResponseDTO = idolImgService.update(imageId, modifyIdolImgRequestDTO);
            model.addAttribute("detailIdolImgResponseDTO", detailIdolImgResponseDTO);
            return "Idol/IdolImgDetail";
        } catch (RuntimeException e) {
            log.warn("idolImage 수정 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage", "patchImage 에러");
            return "Idol/error";
        }
    }

    // 관리자가 이미지 삭제
    @DeleteMapping("/{image-id}")
    public String deleteImage(Model model,
                              @Positive @PathVariable("image-id")Long imageId){

//        Long userId = null;
//        if(authentication != null) userId = Long.parseLong((String) authentication.getPrincipal());
//        if(!idolImgService.isAdmin(userId)){
//            log.warn("어드민이 아닌 회원은 삭제 할 수 없습니다.");
//            model.addAttribute("errorMessage","관리자 권한이 필요합니다.");
//            return "Idol/error";
//        }

        try {
            DetailIdolImgResponseDTO detailIdolImgResponseDTO = idolImgService.remove(imageId);
            model.addAttribute("detailIdolImgResponseDTO",detailIdolImgResponseDTO);
            return "Idol/IdolImage";
        } catch (RuntimeException e) {
            log.warn("idolImage 삭제 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","deleteImage 에러");
            return "Idol/error";
        }
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
