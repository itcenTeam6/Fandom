package com.example.NewJeans.service;

import com.example.NewJeans.dto.request.BoardCreateRequestDTO;
import com.example.NewJeans.dto.response.BoardDetailResponseDTO;
import com.example.NewJeans.dto.response.BoardListResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

//    @BeforeEach
//    void beforeInsert(){
//
//        BoardCreateRequestDTO dto1=BoardCreateRequestDTO.builder()
//                .boardContent("안녕하세요")
//                .boardFile("이미지")
//                .build();
//                //.idol("")
//                //.member()
//        BoardCreateRequestDTO dto2=BoardCreateRequestDTO.builder()
//                .boardContent("안녕하세요2")
//                .boardFile("이미지2")
//                .build();
//
//    }
//
//    @Test
//    @DisplayName("새로운 게시물을 등록하면 생성되는 리스트는 3개 들어있어야 한다.")
//    void createTest(){
//        //given
//        BoardCreateRequestDTO newBoard=BoardCreateRequestDTO.builder()
//                .boardContent("새로운 게시물~")
//                .boardFile("이미지")
//                .build();
//        //when
//
//        BoardListResponseDTO responseDTO=boardService.create(newBoard, 1L,1L);
//
//
//        //then
//        List<BoardDetailResponseDTO> boards = responseDTO.getBoards();
//        assertEquals(3,boards.size());
//
//        System.out.println("===============");
//        boards.forEach(System.out::println);
//    }


}