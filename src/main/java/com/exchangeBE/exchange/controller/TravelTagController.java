package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.TravelTagDto;
import com.exchangeBE.exchange.service.TravelTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travel/tags")
public class TravelTagController {

    @Autowired
    private TravelTagService travelTagService;

    @PostMapping
    public TravelTagDto createTravelTag(@RequestBody TravelTagDto travelTagDto) {
        return travelTagService.createTravelTag(travelTagDto);
    }

    @GetMapping("/{travelPostId}")
    public List<TravelTagDto> getTagsForTravelPost(@PathVariable Long travelPostId) {
        return travelTagService.getTagsForTravelPost(travelPostId);
    }
}
