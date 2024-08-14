package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.Comment;
import com.exchangeBE.exchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndUser(Long id, User user);

    void deleteAllByUser(User user);
}
