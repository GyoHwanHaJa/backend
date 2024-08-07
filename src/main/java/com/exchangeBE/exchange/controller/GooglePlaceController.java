package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.PlaceDto;
import com.exchangeBE.exchange.service.GooglePlacesService;
import com.google.maps.model.AutocompletePrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/places")
public class GooglePlaceController {

    private final GooglePlacesService googlePlaceService;

    @Autowired
    public GooglePlaceController(GooglePlacesService googlePlaceService) {
        this.googlePlaceService = googlePlaceService;
    }

    @GetMapping("/search")
    public List<PlaceDto> searchPlaces(@RequestParam String query) {
        return googlePlaceService.searchPlaces(query);
    }
}

