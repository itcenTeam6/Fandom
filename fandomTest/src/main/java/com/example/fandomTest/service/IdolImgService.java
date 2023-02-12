package com.example.fandomTest.service;

import com.example.fandomTest.repository.IdolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdolImgService {
    private final IdolRepository idolRepository;
}
