package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
