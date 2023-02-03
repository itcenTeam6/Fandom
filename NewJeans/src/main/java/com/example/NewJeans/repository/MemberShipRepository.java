package com.example.NewJeans.repository;

import com.example.NewJeans.Entity.MemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberShipRepository  extends JpaRepository<MemberShip, Long> {
}
