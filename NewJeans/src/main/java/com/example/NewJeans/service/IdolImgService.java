package com.example.NewJeans.service;

import com.example.NewJeans.dto.request.CreateIdolImgRequestDTO;
import com.example.NewJeans.dto.response.DetailIdolImgResponseDTO;
import com.example.NewJeans.dto.response.ListIdolImgResponseDTO;
import com.example.NewJeans.entity.IdolImg;
import com.example.NewJeans.repository.IdolImgRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdolImgService {

    private final IdolImgRepository idolImgRepository;

    public DetailIdolImgResponseDTO create(CreateIdolImgRequestDTO createIdolImgRequestDTO){
        // RequestDTO -> 엔티티
        IdolImg idolImg = createIdolImgRequestDTO.toEntity();
        idolImgRepository.save(idolImg);
        log.info("아이돌 이미지 업로드 완료 : {}",idolImg.getImgId());

        return new DetailIdolImgResponseDTO(idolImg);
    }

    // 상세 아이돌 이미지
    public DetailIdolImgResponseDTO findIdolImg(Long imageId){
        IdolImg idolImg = findVerifiedIdolImg(imageId);
        return new DetailIdolImgResponseDTO(idolImg);
    }

    // 리스트 아이돌 이미지
    public ListIdolImgResponseDTO findIdolImgs(int page, int size, String sort){
        // 페이징처리 + 목록 불러오기
        Page<IdolImg> pageImgs = idolImgRepository.findAll(PageRequest.of(page - 1, size, Sort.by(sort).descending()));

        // responseDTO 리스트로 변환
        List<IdolImg> listImgs = pageImgs.getContent();
        List<DetailIdolImgResponseDTO> listImgResponseDTOs = listImgs
                .stream().
                map(idolImg -> new DetailIdolImgResponseDTO(idolImg))
                .collect(Collectors.toList());

        return ListIdolImgResponseDTO.builder().idolImages(listImgResponseDTOs).build();
    }

    public IdolImg findVerifiedIdolImg(Long imageId){
        Optional<IdolImg> optionalIdolImg = idolImgRepository.findById(imageId);
        return optionalIdolImg.orElseThrow(() -> new RuntimeException("이미지가 존재하지 않습니다."));
    }
}
