package com.example.NewJeans.controller;


import com.example.NewJeans.Entity.Member;
import com.example.NewJeans.dto.request.CreateBoardRequestDTO;
import com.example.NewJeans.dto.request.ModifyBoardRequestDTO;
import com.example.NewJeans.dto.response.DetailBoardResponseDTO;
import com.example.NewJeans.dto.response.DetailIdolResponseDTO;
import com.example.NewJeans.dto.response.ListBoardResponseDTO;
import com.example.NewJeans.service.BoardService;
import com.example.NewJeans.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final IdolService idolService;


    //게시글 조회  (무한스크롤 필요)
    @GetMapping("/{idol-id}")   //아이돌 번호에 따라 페이징
    public String retrieveBoardList(
            Model model,
            Authentication authentication,
            @PathVariable("idol-id") Long idolId,
            @PageableDefault(size = 10, sort = "boardID", direction = Sort.Direction.DESC) Pageable pageable,
            HttpServletRequest request

    )
    {
        if(authentication == null){
            log.warn("로그인을 하세요");
            return "redirect:/";
        }
        Long member = null;
        if (authentication != null) member = Long.parseLong((String) authentication.getPrincipal());
        
        String userId = null;
        
        try{
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if(cookieName.equals("LOGIN_USEREMAIL")){
                    userId=cookie.getValue();
                }

            }
        }catch (Exception e){
            return null;
        }


        
        log.info("memEmail {}",userId);
        log.info("/board/{} Get request!",idolId);
        ListBoardResponseDTO listBoardResponseDTO = boardService.retrieve(idolId,pageable);
        DetailIdolResponseDTO detailIdolResponseDTO = idolService.findIdol(idolId);
        String idolName=detailIdolResponseDTO.getIdolName();
        String idolMainImg=detailIdolResponseDTO.getIdolMainImg();


        //log.info("boardDate :{}",listBoardResponseDTO.getBoards());


        model.addAttribute("IdolId",idolId);
        model.addAttribute("userId",userId);
        model.addAttribute("member",member);
        model.addAttribute("ListBoardResponseDTO", listBoardResponseDTO);
        model.addAttribute("IdolName",idolName);
        model.addAttribute("IdolMainImg",idolMainImg);


        return "board/boardList";
    }

    //게시글 작성 폼으로
    @GetMapping("/{idol-id}/boardWrite")
    public String boardWrite(
            Model model,
            @PathVariable("idol-id") Long idolId
    )
    {
        model.addAttribute("idolId",idolId);
        return "board/boardWrite";
    }


    //게시글 등록 요청
    @PostMapping("/{idol-id}")
    public String createBoard(
            Model model,
            Authentication authentication,
            @PathVariable("idol-id") Long idolId,
            @Validated @ModelAttribute CreateBoardRequestDTO requestDTO,
            RedirectAttributes redirectAttributes,
            @RequestParam("file") List<MultipartFile> fileList,
            BindingResult result,
            HttpServletRequest request

    )
    throws Exception {
        Long userId = null;
        if (authentication != null) userId = Long.parseLong((String) authentication.getPrincipal());

        String userNickName=null;
        try{
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if(cookieName.equals("LOGIN_NICKNAME")){
                    userNickName=cookie.getValue();
                }

            }
        }catch (Exception e){
            return null;
        }
        log.info("userNickName {}",userNickName);
        log.info("/upload POST! - {}", fileList);

        for (MultipartFile file : fileList) {
            log.info("file-name: {}", file.getName());
            log.info("file-origin-name: {}", file.getOriginalFilename());
            log.info("file-size: {}KB", (double) file.getSize() / 1024);
            log.info("file-type: {}", file.getContentType());
            System.out.println("==================================================================");


            if (result.hasErrors()) {
                log.warn("DTO 검증 에러 발생: {}", result.getFieldError());
                model.addAttribute("error", "createBoard 에러");
                return null; //에러페이지

            }

            try {
                Long idol = boardService.create(requestDTO, idolId, file, userId,userNickName); //userId
                redirectAttributes.addAttribute("idol", idol);
                return "redirect:/board/{idol}"; //게시판 페이지로 리다이렉트

            } catch (RuntimeException e) {
                log.error(e.getMessage());
                model.addAttribute("error", "createBoard 에러");

                return null; ////에러페이지
            }
        }
        return null;
    }



    //게시글 삭제 요청    (작성자 OR 관리자일 경우만 삭제)
    @GetMapping("/{idol-id}/{board-id}")
    public String deleteBoard(
            Model model,
            Authentication authentication,
            @PathVariable("board-id") Long boardId,
            @PathVariable("idol-id") Long idolId
    )
    {

        log.info("/board/{} DELETE request!", boardId);

        if (boardId == null) {
            return null; //에러페이지
        }

        try {
            boardService.delete(boardId,idolId); //memId
            return "redirect:/board/{idol-id}"; //게시판 페이지

        } catch (Exception e) {
            log.warn("게시글 삭제 에러 : {}", e.getMessage());
            model.addAttribute("error","deleteBoard 에러");
            return null; //에러페이지

        }


    }

    //게시글 수정 요청   (작성자만 수정 가능)
    @RequestMapping(
            value = "/{idol-id}/{board-id}"
            , method = {RequestMethod.PUT, RequestMethod.PATCH})
    public String updateBoard(
            Model model,
            //@AuthenticationPrincipal Long memId,
            @PathVariable("board-id") Long boardId,
            @PathVariable("idol-id") Long idolId,
            @Validated @RequestBody ModifyBoardRequestDTO requestDTO, BindingResult result, HttpServletRequest request
    ) {
        if (result.hasErrors()) {
            return null; //에러페이지
        }
        log.info("/board/{} {} request", boardId, request.getMethod());
        log.info("modifying dto: {}", requestDTO);

        try {
            boardService.update(boardId,idolId, requestDTO);
            return "redirect:/"; //게시판 페이지

        } catch (Exception e) {
            log.warn("게시글 수정 에러 : {}", e.getMessage());
            model.addAttribute("error","updateBoard 에러");
            return null; //에러페이지

        }

    }


}
