package com.example.fandomTest.repository;

import com.example.fandomTest.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Page<Board> findAllByIdol_IdolIDOrderByBoardDateDesc(Long idolID, PageRequest of);
}
