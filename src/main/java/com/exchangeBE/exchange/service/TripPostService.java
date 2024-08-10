package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.TripPostDto;
import com.exchangeBE.exchange.entity.TagEntity;
import com.exchangeBE.exchange.entity.TripPostEntity;
import com.exchangeBE.exchange.repository.TagRepository;
import com.exchangeBE.exchange.repository.TripPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TripPostService {

    @Autowired
    private TripPostRepository tripPostRepository;

    @Autowired
    private TagRepository tagRepository;

    public TripPostDto saveTrip(TripPostDto tripPostDto) {
        TripPostEntity entity = convertToEntity(tripPostDto);
        TripPostEntity savedEntity = tripPostRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public void addTagToTripPost(Long tripPostId, Long tagId) {
        TripPostEntity tripPost = tripPostRepository.findById(tripPostId)
                .orElseThrow(() -> new RuntimeException("TripPost not found"));
        TagEntity tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tripPost.getTags().add(tag);
        tripPostRepository.save(tripPost);
    }

    public void removeTagFromTripPost(Long tripPostId, Long tagId) {
        TripPostEntity tripPost = tripPostRepository.findById(tripPostId)
                .orElseThrow(() -> new RuntimeException("TripPost not found"));
        TagEntity tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tripPost.getTags().remove(tag);
        tripPostRepository.save(tripPost);
    }

    public List<String> getTagsForTripPost(Long tripPostId) {
        TripPostEntity tripPost = tripPostRepository.findById(tripPostId)
                .orElseThrow(() -> new RuntimeException("TripPost not found"));
        return tripPost.getTags().stream()
                .map(TagEntity::getName)
                .collect(Collectors.toList());
    }

    private TripPostDto convertToDto(TripPostEntity entity) {
        TripPostDto dto = new TripPostDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCountry(entity.getCountry());  // 추가
        dto.setTravelDateStart(entity.getTravelDateStart());  // 추가
        dto.setTravelDateEnd(entity.getTravelDateEnd());  // 추가
        dto.setLocation(entity.getLocation());  // 추가
        dto.setTopic(entity.getTopic());
        dto.setTags(entity.getTags().stream()
                .map(TagEntity::getName)
                .collect(Collectors.toList()));
        dto.setPhotos(entity.getPhotos());
        return dto;
    }

    private TripPostEntity convertToEntity(TripPostDto dto) {
        TripPostEntity entity = new TripPostEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCountry(dto.getCountry());  // 추가
        entity.setTravelDateStart(dto.getTravelDateStart());  // 추가
        entity.setTravelDateEnd(dto.getTravelDateEnd());  // 추가
        entity.setLocation(dto.getLocation());  // 추가
        entity.setTopic(dto.getTopic());
        entity.setTags(dto.getTags().stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseThrow(() -> new RuntimeException("Tag not found")))
                .collect(Collectors.toSet()));
        entity.setPhotos(dto.getPhotos());
        return entity;
    }
}
