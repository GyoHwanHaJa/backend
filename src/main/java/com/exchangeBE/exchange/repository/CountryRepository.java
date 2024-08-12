package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    List<CountryEntity> findByRegion(String region);
}
