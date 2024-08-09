package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.TripPostDto;
import com.exchangeBE.exchange.service.TripPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/trip-posts")
public class TripPostController {

    @Autowired
    private TripPostService tripPostService;

    @PostMapping
    public TripPostDto createTripPost(@ModelAttribute TripPostDto tripPostDto) throws IOException {
        return tripPostService.saveTrip(tripPostDto);
    }

    // 추가된 태그 관련 메서드들
    @PostMapping("/{tripPostId}/tags/{tagId}")
    public void addTagToTripPost(@PathVariable Long tripPostId, @PathVariable Long tagId) {
        tripPostService.addTagToTripPost(tripPostId, tagId);
    }

    @DeleteMapping("/{tripPostId}/tags/{tagId}")
    public void removeTagFromTripPost(@PathVariable Long tripPostId, @PathVariable Long tagId) {
        tripPostService.removeTagFromTripPost(tripPostId, tagId);
    }

    @GetMapping("/{tripPostId}/tags")
    public List<String> getTagsForTripPost(@PathVariable Long tripPostId) {
        return tripPostService.getTagsForTripPost(tripPostId);
    }
}
