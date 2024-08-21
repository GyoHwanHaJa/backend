package com.exchangeBE.exchange.repository.Trip;

import com.exchangeBE.exchange.entity.Trip.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    List<CountryEntity> findByRegion(String region);
    // name으로 CountryEntity를 조회하는 메서드
    Optional<CountryEntity> findByName(String name);
}
