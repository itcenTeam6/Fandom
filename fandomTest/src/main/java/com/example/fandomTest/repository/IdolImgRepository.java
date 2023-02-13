package com.example.fandomTest.repository;

import com.example.fandomTest.entity.Idol;
import com.example.fandomTest.entity.IdolImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdolImgRepository extends JpaRepository<IdolImg, Long> {

    List<IdolImg> findByIdolId(Idol idolID);

}
