package com.example.NewJeans.repository;

import com.example.NewJeans.entity.Idol;
import com.example.NewJeans.entity.IdolImg;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IdolImgRepositoryTest {

    @Autowired
    IdolImgRepository idolImgRepository;

    @Autowired
    IdolRepository idolRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void insertTest(){
        Idol blackPink = Idol.builder().
                idolName("BlackPink").
                idolMainImg("https://www.naver.com/").
                build();

        idolRepository.save(blackPink);

        IdolImg test = IdolImg.builder().
                idolId(blackPink).
                imgPath("https://www.youtube.com").
                msType("yes").
                idolName("지수").build();

        idolImgRepository.save(test);
    }

}