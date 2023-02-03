package com.example.NewJeans.repository;

import com.example.NewJeans.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, Long> {
}
