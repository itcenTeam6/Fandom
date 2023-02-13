package com.example.NewJeans.repository;

import com.example.NewJeans.entity.IdolImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdolImgRepository extends JpaRepository<IdolImg,Long > {
    Page<IdolImg> findAllByIdolName(String idolName, Pageable pageable);
}
