package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.TopicEntity;
import com.exchangeBE.exchange.entity.TripPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TripPostRepository extends JpaRepository<TripPostEntity, Long> {

    // Google Maps API를 사용 예정인 부분은 주석 처리합니다.
    // List<TripPostEntity> findByCountryId(Long countryId);
    // List<TripPostEntity> findByPlaceId(Long placeId);
    List<TripPostEntity> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
    List<TripPostEntity> findByTopic(TopicEntity topic);

}



