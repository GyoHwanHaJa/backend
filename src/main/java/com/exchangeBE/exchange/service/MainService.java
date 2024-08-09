package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.TripPostDto;
import com.exchangeBE.exchange.entity.TripPostEntity;
import com.exchangeBE.exchange.repository.TripPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    @Autowired
    private TripPostRepository tripPostRepository;

    public List<TripPostDto> getAllPosts() {
        return tripPostRepository.findAll().stream()
                .map(this::convertToDto) // TripPostEntity를 TripPostDto로 변환
                .collect(Collectors.toList());
    }

    public TripPostDto getPostById(Long id) {
        TripPostEntity post = tripPostRepository.findById(id).orElse(null);
        return convertToDto(post);
    }

    // 저장 로직 추가
    public TripPostDto savePost(TripPostDto tripPostDto) {
        TripPostEntity entity = convertToEntity(tripPostDto);
        TripPostEntity savedEntity = tripPostRepository.save(entity);
        return convertToDto(savedEntity);
    }

    private TripPostDto convertToDto(TripPostEntity entity) {
        if (entity == null) return null;
        TripPostDto dto = new TripPostDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setTopic(entity.getTopic());
        dto.setPhotos(entity.getPhotos());
        return dto;
    }

    private TripPostEntity convertToEntity(TripPostDto dto) {
        if (dto == null) return null;
        TripPostEntity entity = new TripPostEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setTopic(dto.getTopic());
        entity.setPhotos(dto.getPhotos());
        return entity;
    }
}
