package com.example.fandomTest.repository;

import com.example.fandomTest.entity.Idol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IdolRepositoryTest {

    @Autowired
    IdolRepository idolRepository;

    @Test
    void selectTest(){
        // given, when
        List<Idol> idolList = idolRepository.findAll();

        // then
        assertEquals(idolList.size(), 4);
        assertEquals(idolList.get(0).getIdolName(), "NewJeans");
        out.println(idolList.get(0).toString());
    }

    @Test
    void selectOneTest(){
        Idol idol = idolRepository.findById(1L).orElseThrow(NullPointerException::new);
        out.println(idol.toString());
    }
}