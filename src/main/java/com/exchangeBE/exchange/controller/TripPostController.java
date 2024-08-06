package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.domain.TripPost;
import com.exchangeBE.exchange.dto.TripPostDto;
import com.exchangeBE.exchange.service.TripPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trip")
public class TripPostController {

    @Autowired
    private TripPostService tripPostService;

    @GetMapping
    public List<TripPost> getAllTrips() {
        return tripPostService.findAllTrips();
    }

    @PostMapping
    public TripPost createTrip(@RequestBody TripPostDto tripPostDto) {
        TripPost tripPost = tripPostService.convertToEntity(tripPostDto);
        return tripPostService.saveTrip(tripPost);
    }
}
