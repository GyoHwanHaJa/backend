package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.TravelTagDto;
import com.exchangeBE.exchange.service.TravelTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/travel/{travelId}/tags")
public class TravelTagController {
    private static final Logger logger = LoggerFactory.getLogger(TravelTagController.class);

    @Autowired
    private TravelTagService travelTagService;

    @GetMapping
    public List<TravelTagDto> getTagsByTravelId(@PathVariable("travelId") Long travelId) {
        return travelTagService.getTagsByTravelId(travelId);
    }

    @PostMapping
    public TravelTagDto createTravelTag(@PathVariable("travelId") Long travelId, @RequestBody TravelTagDto travelTagDto) {

        return travelTagService.createTravelTag(travelId, travelTagDto);

    }

    @PutMapping("/{tagId}")
    public TravelTagDto updateTravelTag(@PathVariable("travelId") Long travelId, @PathVariable("tagId") Long tagId, @RequestBody TravelTagDto travelTagDto) {
        return travelTagService.updateTravelTag(travelId, tagId, travelTagDto);
    }

    @DeleteMapping("/{tagId}")
    public void deleteTravelTag(@PathVariable("tagId") Long tagId) {
        travelTagService.deleteTravelTag(tagId);
    }
}
