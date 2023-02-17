package com.example.fandomTest.repository;

import com.example.fandomTest.entity.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberShipRepository extends JpaRepository<MemberShip, Long> {
    MemberShip findByMem_MemID(@Param(value = "memId") Long memId);

    Optional<MemberShip> findByMem_MemIDAndIdol_IdolID(@Param(value = "memId")Long memId, @Param(value = "idolId")Long idolId);
}
