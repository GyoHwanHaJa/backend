package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.TopicEntity;
import com.exchangeBE.exchange.entity.TripPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TripPostRepository extends JpaRepository<TripPostEntity, Long> {
    List<TripPostEntity> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<TripPostEntity> findByTopic(TopicEntity topic);
}
