package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.entity.Trip.Country;
import com.exchangeBE.exchange.service.trip.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public Map<String, List<Country>> getCountries() {
        return countryService.getCountries();
    }

    @GetMapping("/{region}")
    public List<Country> getCountriesByRegion(@PathVariable String region) {
        return countryService.getCountries().getOrDefault(region, List.of());
    }
}