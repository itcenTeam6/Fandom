package com.example.NewJeans.repository;

import com.example.NewJeans.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, Long> {
    boolean existsByMemEmail(String memEmail);
    Member findByMemEmail(String memEmail);
}
