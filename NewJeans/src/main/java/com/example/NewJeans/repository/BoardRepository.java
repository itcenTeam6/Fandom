package com.example.NewJeans.repository;

import com.example.NewJeans.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {


    Optional<Board> findByIdolId(@Param("idolID") Long idolID);
}
