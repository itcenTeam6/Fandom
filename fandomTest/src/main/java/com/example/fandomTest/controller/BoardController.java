package com.example.fandomTest.controller;

import com.example.fandomTest.dto.request.FileRequestDTO;
import com.example.fandomTest.dto.request.PostRequestDTO;
import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.service.BoardService;
import com.example.fandomTest.service.IdolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {
    private final BoardService boardService;
    private final IdolService idolService;

    @GetMapping(value = "/boardList.do")
    public String boardList(
            @RequestParam(value = "idolID") Long idolID,
            Model model
    ) {
        log.info("boardList.do - idolID is {}", idolID);
        Idol idol = idolService.getIdol(idolID);
        model.addAttribute("idol", idol);
        return "board/boardList";
    }

    @GetMapping(value = "/boardWrite.do")
    public String boardWrite(
            @RequestParam(value = "idolID") Long idolID,
            Model model
    ) {
        log.info("boardWrite.do GET- idolID is {}", idolID);
        model.addAttribute("idolID", idolID);
        return "board/boardWrite";
    }

    @PostMapping(value = "/boardWrite.do")
    public String boardPost(
            PostRequestDTO postRequestDTO,
            Model model
    ) throws IOException {
        log.info("boardWrite.do POST - idolID is {}", postRequestDTO.getIdolID());
        model.addAttribute("idolID", postRequestDTO.getIdolID());

        log.info("file-name: {}", postRequestDTO.getInputImg().getName());
        log.info("file-origin-name: {}", postRequestDTO.getInputImg().getOriginalFilename());
        log.info("file-size: {}KB", (double) postRequestDTO.getInputImg().getSize() / 1024);
        log.info("file-type: {}", postRequestDTO.getInputImg().getContentType());

        if (!postRequestDTO.getInputImg().isEmpty()) {
            FileRequestDTO fileRequestDTO = FileRequestDTO.builder()
                    .uuid(UUID.randomUUID().toString())
                    .fileName(postRequestDTO.getInputImg().getOriginalFilename())
                    .contentType(postRequestDTO.getInputImg().getContentType())
                    .build();
            File newFileName = new File(fileRequestDTO.getUuid() + "_" + fileRequestDTO.getFileName());
            postRequestDTO.getInputImg().transferTo(newFileName);
            log.info("file save ???");
        }

        return "board/boardWrite";
    }
}
