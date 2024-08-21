package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.controller.TravelTagController;
import com.exchangeBE.exchange.dto.CountryDto;
import com.exchangeBE.exchange.dto.PlaceDto;
import com.exchangeBE.exchange.dto.TravelDto;
import com.exchangeBE.exchange.dto.TravelTagDto;
import com.exchangeBE.exchange.entity.CountryEntity;
import com.exchangeBE.exchange.entity.TravelEntity;
import com.exchangeBE.exchange.entity.TravelTagEntity;
import com.exchangeBE.exchange.repository.TravelRepository;
import com.exchangeBE.exchange.repository.TravelTagRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private TravelTagRepository travelTagRepository; // 추가된 부분: TravelTagRepository 주입

    @Autowired
    private TravelTagService travelTagService;


    private static final Logger logger = LoggerFactory.getLogger(TravelTagController.class);
    public TravelDto createTravelPost(TravelDto travelDto) {


        TravelEntity travel = new TravelEntity();
        //travel.setUserId(travelDto.getUserId());
        travel.setTitle(travelDto.getTitle());
        travel.setContent(travelDto.getContent());
        travelRepository.save(travel);

        // 생성된 TravelEntity의 ID를 이용하여 TravelTagDto를 생성 및 저장 로직 추가해서 travelpost 만들 때 tag도 같이 하도록
        if (travelDto.getTags() != null) {
            for (TravelTagDto tagDto : travelDto.getTags()) {

                //travelTagService.createTravelTag(travel.getId(), tagDto);
                TravelTagEntity tagEntity = new TravelTagEntity();
                tagEntity.setSubject(tagDto.getSubject());
                tagEntity.setTravelDateStart(tagDto.getTravelDateStart());
                tagEntity.setTravelDateEnd(tagDto.getTravelDateEnd());
                tagEntity.setCountryName(tagDto.getCountryName());
                tagEntity.setPlaceName(tagDto.getPlaceName());

                travel.addTag(tagEntity); // 태그를 TravelEntity에 추가
                travelTagRepository.save(tagEntity); // 태그를 저장

            }
        }

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
        //dto.setUserId(travel.getUserId());
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
                    /*CountryDto countryDto = new CountryDto();
                      if (tag.getCountry() != null) {

                        countryDto.setName(tag.getCountry().getName());

                    }
                    tagDto.setCountry(countryDto);*/
                    //CountryDto countryDto = new CountryDto(tag.getCountryName());
                    tagDto.setCountryName(tag.getCountryName());

                    // Place 설정
                    //PlaceDto placeDto = new PlaceDto(tag.getPlaceName());
                    tagDto.setPlaceName(tag.getPlaceName());


                    tagDto.setSubject(tag.getSubject());
                    tagDto.setTravelDateStart(tag.getTravelDateStart());
                    tagDto.setTravelDateEnd(tag.getTravelDateEnd());
                    return tagDto;
                }).collect(Collectors.toList());

        dto.setTags(tags);

        return dto;
    }
}
