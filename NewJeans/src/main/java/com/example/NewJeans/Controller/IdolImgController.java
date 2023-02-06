package com.example.NewJeans.Controller;


import com.example.NewJeans.dto.request.CreateIdolImgRequestDTO;
import com.example.NewJeans.dto.response.DetailIdolImgResponseDTO;
import com.example.NewJeans.dto.response.ListIdolImgResponseDTO;
import com.example.NewJeans.service.IdolImgService;
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
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/membership")
public class IdolImgController {

    private final IdolImgService idolImgService;

    //멤버쉽 이미지 등록
    @PostMapping
    public ResponseEntity<?> createImage(Model model, Authentication authentication,
                                      @Validated CreateIdolImgRequestDTO createIdolImgRequestDTO,
                                      BindingResult result){
        authentication.getPrincipal(); //나중에 회원 권한 생기면 할것

        //CreateDTO가 잘못 입력된 경우 에러
        if(result.hasErrors()){
            log.warn("createImage 핸들러 메서드 에러발생 : {}", result.getFieldError());
            return ResponseEntity.badRequest().build();
        }

        DetailIdolImgResponseDTO detailIdolImgResponseDTO = idolImgService.create(createIdolImgRequestDTO);

        return new ResponseEntity<>(
                detailIdolImgResponseDTO, HttpStatus.CREATED
        );
    }

    //멤버쉽 이미지 보기
    @GetMapping()
    public ResponseEntity<?> getImages(Model model, Authentication authentication,
                                       @RequestParam(name = "page", required = false, defaultValue = "1")int page,
                                       @RequestParam(name = "size", required = false, defaultValue = "10")int size,
                                       @RequestParam(name = "sort", required = false, defaultValue = "imgId")String sort){
        authentication.getPrincipal(); //나중에 회원 권한 생기면 할것
        ListIdolImgResponseDTO listIdolImgResponseDTO = idolImgService.findIdolImgs(page,size,sort);

        return ResponseEntity.ok().body(listIdolImgResponseDTO);
    }

    // 멤버쉽 이미지 상세 보기
    @GetMapping("/{image-id}")
    public ResponseEntity<?> getImage(Model model,
                           @Positive @PathVariable("image-id") int imageId,
                           Authentication authentication){
        authentication.getPrincipal(); //나중에 회원 권한 생기면 할것



        return ResponseEntity.ok().build();
    }

    // 관리자가 이미지 수정
    @RequestMapping(value="/{image-id}",method = {RequestMethod.PUT,RequestMethod.PATCH})
    public ResponseEntity<?> updateImage(Model model,
                              @Positive @PathVariable("image-id") int imageId,
                              Authentication authentication,
                              BindingResult result){
        authentication.getPrincipal(); //나중에 회원 권한 생기면 할것

        //ModifyDTO가 잘못 입력된 경우 에러
        if(result.hasErrors()){
            log.warn("updateImage 핸들러 메서드 에러 발생 : {}",result.getFieldError());
            return ResponseEntity.badRequest().build();
        }



        return ResponseEntity.ok().build();
    }

    // 관리자가 이미지 삭제
    @DeleteMapping("/{image-id}")
    public ResponseEntity<?> removeImage(Model model,
                              @Positive @PathVariable("image-id")int imageId,
                              Authentication authentication){
        authentication.getPrincipal(); //나중에 회원 권한 생기면 할것


        return ResponseEntity.ok().build();
    }
}
