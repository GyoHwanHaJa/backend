package com.exchangeBE.exchange.repository.trip;

import com.exchangeBE.exchange.entity.trip.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<TravelEntity, Long> {
    // 기본적인 CRUD 메서드는 JpaRepository가 제공
}
