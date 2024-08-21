package com.exchangeBE.exchange.repository.trip;

import com.exchangeBE.exchange.entity.trip.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // 특정 TravelEntity에 속한 모든 댓글을 조회하는 메서드
    List<CommentEntity> findByTravelPostId(Long travelPostId);
}
