package com.example.NewJeans.repository;

import com.example.NewJeans.entity.Idol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IdolRepositoryTest {

    @Autowired
    IdolRepository idolRepository;

    @Test
    void insertTest(){
        Idol ive = Idol.builder().
                idolName("IVE").
                idolMainImg("http://res.heraldm.com/content/image/2021/12/10/20211210000542_0.jpg").build();

        Idol newJeans = Idol.builder().
                idolName("NewJeans").
                idolMainImg("https://images.chosun.com/resizer/gst8Y76TWvb559SjLSh43AmEUrU=/2000x1333/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/GO7X3DGRXZDGAPFJJRGNZBR4VI.jpg").build();

        Idol LESSERAFIM = Idol.builder().
                idolName("LESSERAFIM").
                idolMainImg("https://blog.kakaocdn.net/dn/yPLo3/btrz6qShOq3/Ia3mcJQ7WPrIApAN3ZlH70/img.jpg").build();

        Idol BlackPink = Idol.builder().
                idolName("BlackPink").
                idolMainImg("https://upload.wikimedia.org/wikipedia/commons/e/ee/BLACKPINK_PUBG_Mobile_Sept_2020_ad_%28derived%29.jpg").build();

        idolRepository.save(ive);
        idolRepository.save(newJeans);
        idolRepository.save(LESSERAFIM);
        idolRepository.save(BlackPink);

        List<Idol> idolList = idolRepository.findAll();

        assertEquals(4, idolList.size());
    }
}