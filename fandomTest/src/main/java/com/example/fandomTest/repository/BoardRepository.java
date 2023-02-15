package com.example.fandomTest.repository;

import com.example.fandomTest.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board,Long> {
//    Page<Board> findAllByIdol_IdolIDOrderByBoardDateDesc(Long idolID, PageRequest of);

    Page<Board> findAllByIdol_IdolIDOrderByBoardDateDesc(Long idolId, Pageable pageable);
}
