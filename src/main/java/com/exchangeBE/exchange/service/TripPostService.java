package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.TripPostDto;
import com.exchangeBE.exchange.entity.TopicEntity;
import com.exchangeBE.exchange.entity.TripPostEntity;
import com.exchangeBE.exchange.repository.TripPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripPostService {

    @Autowired
    private TripPostRepository tripPostRepository;

    public List<TripPostDto> findAllTrips() {
        return tripPostRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public TripPostDto saveTrip(TripPostEntity tripPostEntity) {
        tripPostEntity = tripPostRepository.save(tripPostEntity);
        return convertToDto(tripPostEntity);
    }

    public TripPostEntity convertToEntity(TripPostDto tripPostDto) {
        TripPostEntity tripPostEntity = new TripPostEntity();
        tripPostEntity.setTitle(tripPostDto.getTitle());
        tripPostEntity.setDescription(tripPostDto.getDescription());
        tripPostEntity.setStartDate(tripPostDto.getStartDate());
        tripPostEntity.setEndDate(tripPostDto.getEndDate());
        tripPostEntity.setTopic(tripPostDto.getTopic());
        return tripPostEntity;
    }

    public TripPostDto convertToDto(TripPostEntity tripPostEntity) {
        TripPostDto tripPostDto = new TripPostDto();
        tripPostDto.setId(tripPostEntity.getId());
        tripPostDto.setTitle(tripPostEntity.getTitle());
        tripPostDto.setDescription(tripPostEntity.getDescription());
        tripPostDto.setStartDate(tripPostEntity.getStartDate());
        tripPostDto.setEndDate(tripPostEntity.getEndDate());
        tripPostDto.setTopic(tripPostEntity.getTopic());
        return tripPostDto;
    }

    public List<TripPostDto> findByTopic(TopicEntity topicEntity) {
        return tripPostRepository.findByTopic(topicEntity)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TripPostDto> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return tripPostRepository.findByStartDateBetween(startDate, endDate)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}