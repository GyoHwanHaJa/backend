package com.exchangeBE.exchange.repository.Trip;

import com.exchangeBE.exchange.entity.Trip.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<TravelEntity, Long> {
    // 기본적인 CRUD 메서드는 JpaRepository가 제공
}
