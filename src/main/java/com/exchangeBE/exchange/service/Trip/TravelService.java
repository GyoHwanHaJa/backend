package com.exchangeBE.exchange.service.Trip;

import com.exchangeBE.exchange.dto.Trip.CountryDto;
import com.exchangeBE.exchange.dto.Trip.PlaceDto;
import com.exchangeBE.exchange.dto.Trip.TravelDto;
import com.exchangeBE.exchange.dto.Trip.TravelTagDto;
import com.exchangeBE.exchange.entity.Trip.TravelEntity;
import com.exchangeBE.exchange.repository.Trip.TravelRepository;
import com.exchangeBE.exchange.repository.Trip.TravelTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private TravelTagRepository travelTagRepository; // 추가된 부분: TravelTagRepository 주입

    public TravelDto createTravelPost(TravelDto travelDto) {
        TravelEntity travel = new TravelEntity();
        travel.setUserId(travelDto.getUserId());
        travel.setTitle(travelDto.getTitle());
        travel.setContent(travelDto.getContent());

        travelRepository.save(travel);

        return convertToDto(travel);
    }

    public TravelDto updateTravelPost(Long id, TravelDto travelDto) {
        TravelEntity travel = travelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel post not found"));

        travel.setTitle(travelDto.getTitle());
        travel.setContent(travelDto.getContent());
        travel.setUpdatedAt(travelDto.getUpdatedAt());

        travelRepository.save(travel);

        return convertToDto(travel);
    }

    public void deleteTravelPost(Long id) {
        TravelEntity travel = travelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel post not found"));
        travelRepository.delete(travel);
    }

    public TravelDto getTravelPostById(Long id) {
        TravelEntity travel = travelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel post not found"));

        // 조회수 증가
        travel.incrementPageView();
        travelRepository.save(travel);

        return convertToDto(travel);
    }

    public List<TravelDto> getAllTravelPosts() {
        return travelRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void likeTravelPost(Long id) {
        TravelEntity travel = travelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel post not found"));

        travel.incrementLikes();
        travelRepository.save(travel);
    }

    public void unlikeTravelPost(Long id) {
        TravelEntity travel = travelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Travel post not found"));

        travel.decrementLikes();
        travelRepository.save(travel);
    }

    // Entity를 DTO로 변환하는 메서드
    private TravelDto convertToDto(TravelEntity travel) {
        TravelDto dto = new TravelDto();
        dto.setId(travel.getId());
        dto.setUserId(travel.getUserId());
        dto.setTitle(travel.getTitle());
        dto.setContent(travel.getContent());
        dto.setPageView(travel.getPageView());
        dto.setLikes(travel.getLikes());
        dto.setCreatedAt(travel.getCreatedAt());
        dto.setUpdatedAt(travel.getUpdatedAt());


        // 추가된 부분: 태그 정보를 TravelDto로 변환하여 포함
        List<TravelTagDto> tags = travel.getTags().stream()
                .map(tag -> {
                    TravelTagDto tagDto = new TravelTagDto();
                    tagDto.setId(tag.getId());
                    // Country 설정
                    CountryDto countryDto = new CountryDto();
                    if (tag.getCountry() != null) {

                        countryDto.setName(tag.getCountry().getName());

                    }
                    tagDto.setCountry(countryDto);

                    // Place 설정
                    PlaceDto placeDto = new PlaceDto(tag.getPlaceName());
                    tagDto.setPlace(placeDto);


                    tagDto.setSubject(tag.getSubject());
                    tagDto.setTravelDateStart(tag.getTravelDateStart());
                    tagDto.setTravelDateEnd(tag.getTravelDateEnd());
                    return tagDto;
                }).collect(Collectors.toList());

        dto.setTags(tags);

        return dto;
    }
}
