package com.example.fandomTest.service;

import com.example.fandomTest.dto.response.DetailIdolImgResponseDTO;
import com.example.fandomTest.dto.response.ListIdolImgResponseDTO;
import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.entity.IdolImg;
import com.example.fandomTest.entity.MemberShip;
import com.example.fandomTest.repository.IdolImgRepository;
import com.example.fandomTest.repository.IdolRepository;
import com.example.fandomTest.repository.MemberShipRepository;
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
@Slf4j
@RequiredArgsConstructor
public class IdolImgService {
    private final IdolImgRepository idolImgRepository;
    private final IdolRepository idolRepository;
    private final MemberShipRepository memberShipRepository;

    public ListIdolImgResponseDTO findIdolImgs(Long idolID, int page, int size, String sort) {
        Idol idol = idolRepository.findById(idolID).orElseThrow(() -> new RuntimeException("아이돌이 존재하지 않습니다."));

        // 페이징처리 + 목록 불러오기
        Page<IdolImg> pageImgs = idolImgRepository.findAllByIdolId_IdolID(idolID, PageRequest.of(page - 1, size, Sort.by(sort).descending()));

        // responseDTO 리스트로 변환
        List<IdolImg> listImgs = pageImgs.getContent();
        List<DetailIdolImgResponseDTO> listImgResponseDTOs = listImgs
                .stream()
                .map(DetailIdolImgResponseDTO::new)
                .collect(Collectors.toList());

        int startPage = ((pageImgs.getNumber() - 1) / size) * size + 1; //시작 페이지
        int endPage = Math.min(startPage + size - 1, pageImgs.getTotalPages()); //종료 페이지

        return ListIdolImgResponseDTO.builder()
                .idolImages(listImgResponseDTOs)
                .size(pageImgs.getSize())
                .page(pageImgs.getNumber())
                .totalElements(pageImgs.getTotalElements())
                .totalPages(pageImgs.getTotalPages())
                .hasNext(pageImgs.hasNext())
                .hasPrevious(pageImgs.hasPrevious())
                .startPage(startPage)
                .endPage(endPage)
                .build();
    }

    public boolean isMemberShip(Long userId) {
        if(userId == null) return false; // 인증객체가 없다 == 로그인 안했다
        MemberShip memberShip = memberShipRepository.findByMem_MemID(userId); //멤버의 아이디로 멤버쉽의 유형을 가져옴
        // 멤버쉽 회원이거나 관리자면 컨텐츠를 볼 수 있음
        return memberShip != null && (memberShip.getMsType().equals("yes"));
    }

}
