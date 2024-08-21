package com.exchangeBE.exchange.repository.Trip;

import com.exchangeBE.exchange.entity.Trip.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    // 특정 CommentEntity에 속한 모든 답글을 조회하는 메서드
    List<ReplyEntity> findByCommentId(Long commentId);
}
