package com.example.fandomTest.service;

import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.repository.IdolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdolService {
    private final IdolRepository idolRepository;

    public List<Idol> getIdolList() {
        List<Idol> idolList = idolRepository.findAll();
        if (idolList.isEmpty()) {
            throw new RuntimeException("idolList is Empty");
        }
        return idolList;
    }

    public Idol getIdol(final Long idolID){
        log.info("Idol getIdol");
        return idolRepository.findById(idolID).orElseThrow(NullPointerException::new);
    }
}
