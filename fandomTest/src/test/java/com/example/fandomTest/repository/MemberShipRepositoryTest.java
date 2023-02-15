package com.example.fandomTest.repository;

import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.entity.Member;
import com.example.fandomTest.entity.MemberShip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberShipRepositoryTest {

    @Autowired
    MemberShipRepository memberShipRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    IdolRepository idolRepository;
}