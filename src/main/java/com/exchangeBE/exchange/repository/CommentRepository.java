package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPostId(Long postId);  // 이 부분은 게시글 ID로 댓글을 조회하는 메서드입니다.
}
