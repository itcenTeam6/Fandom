package com.example.NewJeans.repository;

import com.example.NewJeans.Entity.Idol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdolRepository  extends JpaRepository<Idol,Long > {
    Optional<Idol> findByIdolName(String idolName);
}
