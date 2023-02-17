package com.example.fandomTest.repository;

import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.entity.IdolImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdolImgRepository extends JpaRepository<IdolImg, Long> {

    Page<IdolImg> findAllByIdolId_IdolID(Long imgId, Pageable pageable);
}
