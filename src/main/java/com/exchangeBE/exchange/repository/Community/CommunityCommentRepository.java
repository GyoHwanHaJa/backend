package com.exchangeBE.exchange.repository.Community;

import com.exchangeBE.exchange.entity.Community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommunityCommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardId(Long boardId);
}
