package com.exchangeBE.exchange.controller.trip;


import com.exchangeBE.exchange.entity.trip.CountryEntity;
import com.exchangeBE.exchange.service.trip.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public Map<String, List<CountryEntity>> getCountries() {
        return countryService.getCountries();
    }

    @GetMapping("/{region}")
    public List<CountryEntity> getCountriesByRegion(@PathVariable String region) {
        return countryService.getCountries().getOrDefault(region, List.of());
    }
}
