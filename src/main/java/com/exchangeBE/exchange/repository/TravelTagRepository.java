package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.TravelTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelTagRepository extends JpaRepository<TravelTagEntity, Long> {
    List<TravelTagEntity> findByTravelPostId(Long travelPostId);
}
