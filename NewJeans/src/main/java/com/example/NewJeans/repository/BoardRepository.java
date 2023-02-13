package com.example.NewJeans.repository;

import com.example.NewJeans.Entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board,Long> {

    @Query("select t from Board t where t.idol=:idol")
        //List<Board> findByIdolId(@Param("idol") Long idolId);
    Page<Board> findByIdolId(@Param("idol") Long idolId, Pageable pageable);

    /// List<Board> findByIdolContaining(Long idol, Pageable pageable);


//    @Modifying
//    @Query("update Board b set b.boardCnt=b.boardCnt+1 where b.boardid=:boardid")
}