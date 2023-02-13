package com.example.fandomTest.service;

import com.example.fandomTest.controller.IdolController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdolService {
    private IdolController idolController;
}
