package com.example.fandomTest.repository;

import com.example.fandomTest.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Transactional
    void boardListTest(){
        Page<Board> boardItems = boardRepository.findAllByIdol_IdolIDOrderByBoardDateDesc(1L, PageRequest.of(0, 8));

        List<Board> boards = boardItems.getContent();

        boards.forEach(System.out::println);
    }
}