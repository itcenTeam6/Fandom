package com.example.NewJeans.service;

import com.example.NewJeans.dto.response.DetailIdolImgResponseDTO;
import com.example.NewJeans.dto.response.ListIdolImgResponseDTO;
import com.example.NewJeans.Entity.Idol;
import com.example.NewJeans.Entity.IdolImg;
import com.example.NewJeans.Entity.Member;
import com.example.NewJeans.Entity.MemberShip;
import com.example.NewJeans.repository.IdolImgRepository;
import com.example.NewJeans.repository.IdolRepository;
import com.example.NewJeans.repository.MemberRepository;
import com.example.NewJeans.repository.MemberShipRepository;
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
    private final MemberRepository memberRepository;

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

    public boolean isMemberShip(Long memId, Long idolId) {
        Optional<MemberShip> optionalMemberShip = memberShipRepository.findByMem_MemIDAndIdol_IdolID(memId, idolId);
        if(optionalMemberShip.isEmpty()) return false; //커뮤니티 멤버가 아니면 거짓
        MemberShip memberShip = optionalMemberShip.get();
        return memberShip.getMsType().equals("yes"); //멤버쉽 회원이면 참, 멤버쉽 회원이 아니면 거짓
    }

    public boolean isMemberShipForBoard(String userEmail, Long idolId) {
        Member byMemEmail = memberRepository.findByMemEmail(userEmail);
        Optional<MemberShip> optionalMemberShip = memberShipRepository.findByMem_MemIDAndIdol_IdolID(byMemEmail.getMemID(), idolId);
        if(optionalMemberShip.isEmpty()) return false; //커뮤니티 멤버가 아니면 거짓
        MemberShip memberShip = optionalMemberShip.get();
        return memberShip.getMsType().equals("yes"); //멤버쉽 회원이면 참, 멤버쉽 회원이 아니면 거짓
    }

    public void addMemberShip(String userEmail, Long idolId) {
        Member byMemEmail = memberRepository.findByMemEmail(userEmail);
        Idol byIdolID = idolRepository.findById(idolId).orElseThrow(() -> new RuntimeException("아이돌 없따"));

        Optional<MemberShip> optionalMemberShip = memberShipRepository.findByMem_MemIDAndIdol_IdolID(byMemEmail.getMemID(), idolId);

        if (optionalMemberShip.isEmpty()) {
            memberShipRepository.save(MemberShip.builder()
                    .msType("yes")
                    .mem(byMemEmail)
                    .idol(byIdolID)
                    .build());
        }else {
            MemberShip memberShip = optionalMemberShip.orElseThrow(() -> new RuntimeException("존재하지 않는 멤버회원입니다."));
            memberShip.setMsType("yes");
            memberShipRepository.save(memberShip);
        }
    }
}

