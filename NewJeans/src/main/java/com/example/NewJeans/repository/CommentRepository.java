package com.example.NewJeans.repository;

import com.example.NewJeans.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

