package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.Trip.TravelDto;
import com.exchangeBE.exchange.service.Trip.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping
    public List<TravelDto> getAllTravelPosts() {
        return travelService.getAllTravelPosts();
    }

    @GetMapping("/{id}")
    public TravelDto getTravelPostById(@PathVariable Long id) {
        return travelService.getTravelPostById(id);
    }

    @PostMapping
    public TravelDto createTravelPost(@RequestBody TravelDto travelDto) {
        return travelService.createTravelPost(travelDto);
    }

    @PutMapping("/{id}")
    public TravelDto updateTravelPost(@PathVariable Long id, @RequestBody TravelDto travelDto) {
        return travelService.updateTravelPost(id, travelDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTravelPost(@PathVariable Long id) {
        travelService.deleteTravelPost(id);
    }

    @PostMapping("/{id}/like")
    public void likeTravelPost(@PathVariable Long id) {
        travelService.likeTravelPost(id);
    }

    @PostMapping("/{id}/unlike")
    public void unlikeTravelPost(@PathVariable Long id) {
        travelService.unlikeTravelPost(id);
    }
}
