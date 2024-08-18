package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.CountryDto;
import com.exchangeBE.exchange.dto.PlaceDto;
import com.exchangeBE.exchange.dto.TravelTagDto;
import com.exchangeBE.exchange.entity.CountryEntity;
import com.exchangeBE.exchange.entity.PlaceEntity;
import com.exchangeBE.exchange.entity.TravelEntity;
import com.exchangeBE.exchange.entity.TravelTagEntity;
import com.exchangeBE.exchange.repository.CountryRepository;
import com.exchangeBE.exchange.repository.PlaceRepository;
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


    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public TravelTagDto createTravelTag(TravelTagDto travelTagDto) {
        TravelEntity travelPost = travelRepository.findById(travelTagDto.getTravelPostId())
                .orElseThrow(() -> new RuntimeException("Travel post not found"));

        // CountryEntity를 조회하여 설정
        CountryEntity country = countryRepository.findByName(travelTagDto.getCountry().getName())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        // PlaceEntity를 조회하거나 새로 생성하여 설정
        PlaceEntity place = placeRepository.findByName(travelTagDto.getPlace().getName())
                .orElseGet(() -> {
                    PlaceEntity newPlace = new PlaceEntity();
                    newPlace.setName(travelTagDto.getPlace().getName());
                    return placeRepository.save(newPlace); // 새로 생성된 PlaceEntity를 저장
                });


        TravelTagEntity tagEntity = new TravelTagEntity();

        tagEntity.setTravelPost(travelPost);
        tagEntity.setCountry(country);  // 조회된 CountryEntity 설정



        tagEntity.setPlaceName(travelTagDto.getPlace().getName());  // PlaceDto에서 이름을 추출해 설정
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
        dto.setSubject(tagEntity.getSubject());
        dto.setTravelDateStart(tagEntity.getTravelDateStart());
        dto.setTravelDateEnd(tagEntity.getTravelDateEnd());

        // CountryEntity를 CountryDto로 변환하여 설정
        CountryDto countryDto = new CountryDto();
        CountryEntity country = tagEntity.getCountry();
        if (country != null) {
            // name 필드만 설정
            countryDto.setName(country.getName());
        }
        dto.setCountry(countryDto);

        // PlaceDto를 생성하고 이름만 설정
        PlaceDto placeDto = new PlaceDto(tagEntity.getPlaceName());
        dto.setPlace(placeDto);


        return dto;
    }
}
