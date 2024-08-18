package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
    Optional<PlaceEntity> findByName(String name);
}
