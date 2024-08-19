package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.TravelTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelTagRepository extends JpaRepository<TravelTagEntity, Long> {
    List<TravelTagEntity> findByTravelId(Long travelId);
}
