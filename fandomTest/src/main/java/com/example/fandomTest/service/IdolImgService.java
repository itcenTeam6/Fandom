package com.example.fandomTest.service;

import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.entity.IdolImg;
import com.example.fandomTest.repository.IdolImgRepository;
import com.example.fandomTest.repository.IdolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdolImgService {
    private final IdolImgRepository idolImgRepository;

    private final IdolRepository idolRepository;

    public List<IdolImg> selectIdolImg(Long idolID){
        Idol idol = idolRepository.findById(idolID).orElseThrow(NullPointerException::new);
        List<IdolImg> idolImgList = idolImgRepository.findByIdolId(idol);
        
        // 임시용
        return idolImgList.stream().limit(20).collect(Collectors.toList());
    }
}
