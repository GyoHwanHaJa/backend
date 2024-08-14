package com.exchangeBE.exchange.repository.Trip;

import com.exchangeBE.exchange.entity.Trip.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository  extends JpaRepository<Country, Long> {
    List<Country> findByRegion(String region);
}
