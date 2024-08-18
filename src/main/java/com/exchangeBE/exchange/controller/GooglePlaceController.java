package com.exchangeBE.exchange.controller;


import com.exchangeBE.exchange.service.GooglePlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/places")
public class GooglePlaceController {

    private final GooglePlacesService googlePlaceService;

    @Autowired
    public GooglePlaceController(GooglePlacesService googlePlaceService) {
        this.googlePlaceService = googlePlaceService;
    }

    @GetMapping("/find")
    public String findPlace(@RequestParam String query) {
        return googlePlaceService.findPlaceFromText(query);
    }
}
