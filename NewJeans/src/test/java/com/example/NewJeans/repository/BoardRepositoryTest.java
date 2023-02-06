package com.example.NewJeans.repository;

import com.example.NewJeans.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

//    @BeforeEach
//    void insertTest(){
//        Board board1=Board.builder().boardContent("안녕하세요").build();
//        Board board2=Board.builder().boardContent("하이요").build();
//        boardRepository.save(board1);
//        boardRepository.save(board2);
//
//    }

//    @Test
//    @DisplayName("게시물 등록해야함")
//    void saveBoardTest(){
//
//        Board board=new Board();
//
//        board.setBoardContent("안녕");
//        board.setBoardFile("바이바이");
//
//        Board newBoard=boardRepository.save(board);
//
//
//    }


}