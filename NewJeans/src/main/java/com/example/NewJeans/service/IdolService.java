package com.example.NewJeans.service;

import com.example.NewJeans.Entity.Idol;
import com.example.NewJeans.dto.request.CreateIdolRequestDTO;
import com.example.NewJeans.dto.request.ModifyIdolRequestDTO;
import com.example.NewJeans.dto.response.DetailIdolResponseDTO;
import com.example.NewJeans.dto.response.ListIdolResponseDTO;
import com.example.NewJeans.repository.IdolRepository;
import com.example.NewJeans.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class IdolService {
    private final IdolRepository idolRepository;
    private static final String IMAGE_PATH = "E:\\image";

    public DetailIdolResponseDTO create(CreateIdolRequestDTO createIdolRequestDTO){
        //이미지 파일 -> 경로
        createIdolRequestDTO.setIdolMainImg( FileUtils.uploadFile(createIdolRequestDTO.getImage(), IMAGE_PATH));
        //DB에 아이돌 저장
        Idol savedIdol = idolRepository.save(createIdolRequestDTO.toEntity());

        log.info("아이돌 save 완료 : {}",savedIdol);
        return new DetailIdolResponseDTO(savedIdol);
    }

    public ListIdolResponseDTO findIdols(int page, int size, String sort){
        return ListIdolResponseDTO
                .builder()
                .idols(idolRepository
                        .findAll(PageRequest.of(page - 1, size, Sort.by(sort).descending()))
                        .getContent()
                        .stream()
                        .map(DetailIdolResponseDTO::new)
                        .collect(Collectors.toList()))
                .build();
    }

    public DetailIdolResponseDTO findIdol(Long idolId){
        return new DetailIdolResponseDTO(findVerfiedIdol(idolId));
    }

    public DetailIdolResponseDTO updateIdol(Long idolId, ModifyIdolRequestDTO modifyIdolRequestDTO){
        Idol verifiedIdol = findVerfiedIdol(idolId);
        Optional.ofNullable(modifyIdolRequestDTO.getIdolName())
                .ifPresent(verifiedIdol::setIdolName);
        Optional.ofNullable(modifyIdolRequestDTO.getImage())
                .ifPresent(file -> verifiedIdol.setIdolMainImg(FileUtils.uploadFile(file,IMAGE_PATH)));
        return new DetailIdolResponseDTO(idolRepository.save(verifiedIdol));
    }

    public DetailIdolResponseDTO removeIdol(Long idolId){
        Idol verifiedIdol = findVerfiedIdol(idolId);
        idolRepository.delete(verifiedIdol);
        log.info("아이돌 삭제 : {}", verifiedIdol);
        return new DetailIdolResponseDTO(verifiedIdol);
    }

    public Idol findVerfiedIdol(Long idolId){
        return idolRepository.findById(idolId).orElseThrow(() -> new RuntimeException("아이돌이 존재하지 않습니다."));
    }

    public List<Idol> getIdolList() {
        List<Idol> idolList = idolRepository.findAll();
        if (idolList.isEmpty()) {
            throw new RuntimeException("idolList is Empty");
        }
        return idolList;
    }

    public Idol getIdol(final Long idolID){
        return idolRepository.findById(idolID).orElseThrow(() -> new RuntimeException("아이돌이 존재하지 않습니다."));
    }
}