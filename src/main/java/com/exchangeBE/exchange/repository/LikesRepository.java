package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.Board;
import com.exchangeBE.exchange.entity.Comment;
import com.exchangeBE.exchange.entity.Likes;
import com.exchangeBE.exchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByBoardAndUser(Board board, User user);
    Optional<Likes> findByCommentAndUser(Comment comment, User user);

    void deleteAllByUser(User user);
}
