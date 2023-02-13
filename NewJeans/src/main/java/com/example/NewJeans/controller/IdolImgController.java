package com.example.NewJeans.controller;


import com.example.NewJeans.dto.request.CreateIdolImgRequestDTO;
import com.example.NewJeans.dto.request.ModifyIdolImgRequestDTO;
import com.example.NewJeans.dto.response.DetailIdolImgResponseDTO;
import com.example.NewJeans.dto.response.ListIdolImgResponseDTO;
import com.example.NewJeans.service.IdolImgService;
import com.example.NewJeans.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/membership")
public class IdolImgController {
    private final IdolImgService idolImgService;
    private static final String UPLOAD_PATH = "E:\\img";

    @PostMapping("/upload-form")
    public String uploadForm(Model model, Authentication authentication,
                             @Validated @RequestBody CreateIdolImgRequestDTO createIdolImgRequestDTO){

        // 관리자가 아니라면 업로드 불가능
        Long userId = null;
        if(authentication != null) userId = Long.parseLong((String) authentication.getPrincipal());
        if(!idolImgService.isAdmin(userId)){
            log.warn("어드민이 아닌 회원은 업로드 할 수 없습니다.");
            return "Idol/IdolImg";
        }


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
                            HttpServletRequest request){
        log.info("/upload POST! - {}", fileList);

        for (MultipartFile file: fileList) {
            log.info("file-name: {}", file.getName());
            log.info("file-origin-name: {}", file.getOriginalFilename());
            log.info("file-size: {}KB", (double) file.getSize() / 1024);
            log.info("file-type: {}", file.getContentType());
            System.out.println("==================================================================");

            Long imgId = Long.parseLong((String)request.getAttribute("imgId"));
            String imgPath = FileUtils.uploadFile(file, UPLOAD_PATH);

        }

        return "redirect:/member/upload/upload-form";
    }

    //멤버쉽 이미지 보기
    @GetMapping("/{idol-name}")
    public String getImages(Model model, Authentication authentication,
                            @Positive @PathVariable("idol-name") String idolName,
                            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                            @RequestParam(name = "sort", required = false, defaultValue = "imgId") String sort){

        Long userId = null;
        if(authentication != null) userId = Long.parseLong((String) authentication.getPrincipal());
        log.info("현재 {}로 시작합니다.", userId);
        boolean contents = idolImgService.isMemberShip(userId);
        model.addAttribute("contents",contents);


        try {
            ListIdolImgResponseDTO listIdolImgResponseDTO = idolImgService.findIdolImgs(idolName,page,size,sort);
            model.addAttribute("listIdolImgResponseDTO",listIdolImgResponseDTO);
            return "Idol/IdolImg";
        } catch (RuntimeException e) {
            log.warn("idolImage GET(리스트) 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","getImages 에러");
            return "Idol/error";
        }


    }

    // 멤버쉽 이미지 상세 보기
    @GetMapping("/{idol-name}/{image-id}")
    public String getImage(Model model,
                           @Positive @PathVariable("idol-name") String idolName,
                           @Positive @PathVariable("image-id") Long imageId){
        log.info("이미지 상세 보기 실행");

        try {
            DetailIdolImgResponseDTO detailIdolImgResponseDTO = idolImgService.findIdolImg(idolName, imageId);
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
//        authentication.getPrincipal(); //나중에 회원 권한 생기면 할것

        try {
            DetailIdolImgResponseDTO detailIdolImgResponseDTO = idolImgService.remove(imageId);
            model.addAttribute("detailIdolImgResponseDTO",detailIdolImgResponseDTO);
            return "Idol/IdolImg";
        } catch (RuntimeException e) {
            log.warn("idolImage 삭제 에러 : {}", e.getMessage());
            model.addAttribute("errorMessage","deleteImage 에러");
            return "Idol/error";
        }
    }
}
