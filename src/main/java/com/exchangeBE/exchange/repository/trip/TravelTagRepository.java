package com.exchangeBE.exchange.repository.trip;

import com.exchangeBE.exchange.entity.trip.TravelTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelTagRepository extends JpaRepository<TravelTagEntity, Long> {

    // 특정 여행 게시글 ID에 해당하는 모든 태그를 가져오는 메서드
    List<TravelTagEntity> findByTravel_Id(Long travelId);
}
