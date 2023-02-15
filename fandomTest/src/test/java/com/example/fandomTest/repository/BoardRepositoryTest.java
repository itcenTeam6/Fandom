package com.example.fandomTest.repository;

import com.example.fandomTest.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;
    @Test
    @Transactional
    void ListTEST(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Board> byIdolId = boardRepository.findAllByIdol_IdolIDOrderByBoardDateDesc(1L, pageable);
        List<Board> boards = byIdolId.getContent();
        boards.forEach(System.out::println);
    }

    @Test
    void tempTest(){
        String temp = "E:/Fandom/fandomTest/src/main/resources/static/img/userImg/f54544d4-a30b-4178-943a-721e822d5461.jpg";
        String split = temp.split("/static")[1];
        System.out.println(split);

    }
}