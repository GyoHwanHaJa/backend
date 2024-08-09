package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    List<ReplyEntity> findByCommentId(Long commentId);
}
