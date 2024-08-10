package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.TravelTagDto;
import com.exchangeBE.exchange.entity.TravelEntity;
import com.exchangeBE.exchange.entity.TravelTagEntity;
import com.exchangeBE.exchange.repository.TravelRepository;
import com.exchangeBE.exchange.repository.TravelTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelTagService {

    @Autowired
    private TravelTagRepository travelTagRepository;

    @Autowired
    private TravelRepository travelRepository;

    public TravelTagDto createTravelTag(TravelTagDto travelTagDto) {
        TravelEntity travelPost = travelRepository.findById(travelTagDto.getTravelPostId())
                .orElseThrow(() -> new RuntimeException("Travel post not found"));

        TravelTagEntity tagEntity = new TravelTagEntity();
        tagEntity.setTravelPost(travelPost);
        tagEntity.setCountry(travelTagDto.getCountry());
        tagEntity.setLocation(travelTagDto.getLocation());
        tagEntity.setSubject(travelTagDto.getSubject());
        tagEntity.setTravelDateStart(travelTagDto.getTravelDateStart());
        tagEntity.setTravelDateEnd(travelTagDto.getTravelDateEnd());

        travelTagRepository.save(tagEntity);

        return convertToDto(tagEntity);
    }

    public List<TravelTagDto> getTagsForTravelPost(Long travelPostId) {
        return travelTagRepository.findByTravelPostId(travelPostId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TravelTagDto convertToDto(TravelTagEntity tagEntity) {
        TravelTagDto dto = new TravelTagDto();
        dto.setId(tagEntity.getId());
        dto.setTravelPostId(tagEntity.getTravelPost().getId());
        dto.setCountry(tagEntity.getCountry());
        dto.setLocation(tagEntity.getLocation());
        dto.setSubject(tagEntity.getSubject());
        dto.setTravelDateStart(tagEntity.getTravelDateStart());
        dto.setTravelDateEnd(tagEntity.getTravelDateEnd());
        return dto;
    }
}
