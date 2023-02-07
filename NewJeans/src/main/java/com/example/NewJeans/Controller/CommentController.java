package com.example.NewJeans.Controller;

import com.example.NewJeans.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/comment")
public class CommentController {

    private final CommentService commentService;



}
