package com.example.NewJeans.controller;


import com.example.NewJeans.Entity.Idol;
import com.example.NewJeans.dto.request.CreateBoardRequestDTO;
import com.example.NewJeans.dto.request.ModifyBoardRequestDTO;
import com.example.NewJeans.dto.response.DetailBoardUpdateResponseDTO;
import com.example.NewJeans.dto.response.ListBoardResponseDTO;
import com.example.NewJeans.service.BoardService;
import com.example.NewJeans.service.IdolImgService;
import com.example.NewJeans.service.IdolService;
import com.example.NewJeans.service.MemberServcie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final IdolService idolService;
    private final IdolImgService idolImgService;

    //게시글 조회  (무한스크롤 필요)
    @GetMapping("/{idol-id}")   //아이돌 번호에 따라 페이징
    public String retrieveBoardList(
            Model model,
            Authentication authentication,
            @PathVariable("idol-id") Long idolId,
            @CookieValue(value = "LOGIN_USEREMAIL", required = false) Cookie userEmail,
            @CookieValue(value = "LOGIN_NICKNAME", required = false) Cookie userNick,
            @PageableDefault(size = 10, sort = "boardID", direction = Sort.Direction.DESC) Pageable pageable,
            HttpServletRequest request
    )
    {
        if(authentication == null){
            log.warn("로그인을 하세요");
            return "redirect:/";
        }
        log.info("memEmail {}", userEmail.getValue());
        log.info("userNick {}", userNick.getValue());
        log.info("/board/{} Get request!", idolId);

        // idolID attribute
        model.addAttribute("idolID", idolId);

        // membership 여부 attribute
        boolean memberShipForBoard = idolImgService.isMemberShipForBoard(userEmail.getValue(), idolId);
        model.addAttribute("memberShipForBoard", memberShipForBoard);

        // idol 객체 attribute
        Idol idol = idolService.getIdol(idolId);
        model.addAttribute("idol", idol);

        // member attribute
        model.addAttribute("userEmail", userEmail.getValue());
        model.addAttribute("userNick", userNick.getValue());

        // boardList attribute
        ListBoardResponseDTO listBoardResponseDTO = boardService.retrieve(idolId, pageable);
        model.addAttribute("listBoard", listBoardResponseDTO);

        return "board/boardList";
    }

    //게시글 작성 폼으로
    @GetMapping("/{idol-id}/boardWrite")
    public String boardWrite(
            Model model,
            @PathVariable("idol-id") Long idolId,
            Authentication authentication
    )
    {
        if(authentication == null){
            log.warn("로그인을 하세요");
            return "redirect:/";
        }

        model.addAttribute("idolID", idolId);
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
            HttpServletRequest request,
            @CookieValue(value = "LOGIN_NICKNAME", required = false) Cookie userNick
    )
    throws Exception {
        Long userId = null;
        if (authentication != null) userId = Long.parseLong((String) authentication.getPrincipal());

//        String userNickName=null;
//        try{
//            Cookie[] cookies = request.getCookies();
//            for (Cookie cookie : cookies) {
//                String cookieName = cookie.getName();
//                if(cookieName.equals("LOGIN_USERNICK")){
//                    userNickName=cookie.getValue();
//                }
//            }
//        }catch (Exception e){
//            return null;
//        }

        log.info("userNickName {}",userNick.getValue());
        log.info("/upload POST! - {}", fileList);

        for (MultipartFile file : fileList) {
//            log.info("file-name: {}", file.getName());
            log.info("file-origin-name: {}", file.getOriginalFilename());
            log.info("file-size: {}KB", (double) file.getSize() / 1024);
//            log.info("file-type: {}", file.getContentType());
            System.out.println("==================================================================");

            if (result.hasErrors()) {
                log.warn("DTO 검증 에러 발생: {}", result.getFieldError());
                model.addAttribute("error", "createBoard 에러");
                return "idol/error"; //에러페이지
            }
            try {
                boolean fileExist = file.getSize() != 0L;

                Long idol = boardService.create(requestDTO, idolId, file, userId, userNick.getValue(), fileExist); //userId
                redirectAttributes.addAttribute("idol", idol);
                return "redirect:/board/{idol}"; //게시판 페이지로 리다이렉트

            } catch (RuntimeException e) {
                log.error(e.getMessage());
                model.addAttribute("error", "createBoard 에러");
                return "idol/error"; ////에러페이지
            }
        }
        return "idol/error";
    }

    //게시글 삭제 요청 (작성자 OR 관리자일 경우만 삭제)
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
            return "idol/error"; //에러페이지
        }
        try {
            boardService.delete(boardId,idolId); //memId
            return "redirect:/board/{idol-id}"; //게시판 페이지

        } catch (Exception e) {
            log.warn("게시글 삭제 에러 : {}", e.getMessage());
            model.addAttribute("error","deleteBoard 에러");
            return "idol/error";//에러페이지
        }
    }

    //게시글 수정 요청   (작성자만 수정 가능)
//    @RequestMapping(value = "/{idol-id}/{board-id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    @PostMapping(value = "/updatePost")
    public String updateBoard(
            Model model,
            @Validated final ModifyBoardRequestDTO requestDTO,
            BindingResult result, HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "idol/error"; //에러페이지
        }
        log.info("/board/{} {} request", requestDTO.getBoardID(), request.getMethod());
//        log.info("modifying dto: {}", requestDTO);

        for (MultipartFile file : requestDTO.getBoardFile()) {
            log.info("file-origin-name: {}", file.getOriginalFilename());
            log.info("file-size: {}KB", (double) file.getSize() / 1024);
            System.out.println("==================================================================");

            if (result.hasErrors()) {
                log.warn("DTO 검증 에러 발생: {}", result.getFieldError());
                return "idol/error"; //에러페이지
            }

            try {
                boolean fileExist = file.getSize() != 0L;

                boardService.update(requestDTO.getBoardID(), requestDTO.getBoardContent(), file, fileExist);
                redirectAttributes.addAttribute("idol", requestDTO.getIdolID());
                return "redirect:/board/{idol}"; //게시판 페이지

            } catch (Exception e) {
                log.warn("게시글 수정 에러 : {}", e.getMessage());
                model.addAttribute("error","updateBoard 에러");
                return "idol/error"; //에러페이지

            }
        }
        return "idol/error";
    }

    @GetMapping(value = "/updateForm")
    public String boardUpdate(
            @RequestParam(value = "boardId") Long boardId,
            @RequestParam(value = "idolID") Long idolId,
            Model model
    ){
        DetailBoardUpdateResponseDTO responseDTO = boardService.boardUpdate(boardId);
        model.addAttribute("idolID", idolId);
        model.addAttribute("responseDTO", responseDTO);
        return "board/boardUpdate";
    }
}
