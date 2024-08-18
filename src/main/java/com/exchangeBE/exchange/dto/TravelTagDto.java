package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.dto.CountryDto;
import com.exchangeBE.exchange.dto.PlaceDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TravelTagDto {
    private Long id;
    private Long travelPostId;

    private CountryDto country; // CountryDto를 추가

    private String subject;
    private LocalDateTime travelDateStart;
    private LocalDateTime travelDateEnd;

    private PlaceDto place;     // PlaceDto 포함
}
