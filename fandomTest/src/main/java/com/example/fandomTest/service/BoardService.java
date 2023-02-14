package com.example.fandomTest.service;

import com.example.fandomTest.dto.request.PostSaveRequestDTO;
import com.example.fandomTest.entity.Board;
import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.entity.Member;
import com.example.fandomTest.repository.BoardRepository;
import com.example.fandomTest.repository.IdolRepository;
import com.example.fandomTest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final IdolRepository idolRepository;
    private final MemberRepository memberRepository;

    public void create(
            PostSaveRequestDTO postSaveRequestDTO
    ){
        Board board = postSaveRequestDTO.toEntity();

        Idol idol = idolRepository.findById(postSaveRequestDTO.getIdolId()).orElseThrow(NullPointerException::new);
        Member member = memberRepository.findByMemEmail(postSaveRequestDTO.getUserEmail());

        board.setIdol(idol);
        board.setMember(member);

        boardRepository.save(board);
    }


}
