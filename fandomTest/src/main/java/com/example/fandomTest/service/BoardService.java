package com.example.fandomTest.service;

import com.example.fandomTest.dto.request.PostSaveRequestDTO;
import com.example.fandomTest.dto.response.DetailBoardResponseDTO;
import com.example.fandomTest.dto.response.ListBoardResponseDTO;
import com.example.fandomTest.entity.Board;
import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.entity.Member;
import com.example.fandomTest.repository.BoardRepository;
import com.example.fandomTest.repository.IdolRepository;
import com.example.fandomTest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public ListBoardResponseDTO retrieve(Long idolID, Pageable pageable){
        log.info("BoardList Service retrieve");

        Page<Board> listBoards = boardRepository.findAllByIdol_IdolIDOrderByBoardDateDesc(idolID, pageable);

        List<DetailBoardResponseDTO> dtoList = listBoards.stream()
                .map(DetailBoardResponseDTO::new)
                .collect(Collectors.toList());

        return ListBoardResponseDTO.builder()
                .boards(dtoList)
                .build();
    }
}
