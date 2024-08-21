package com.exchangeBE.exchange.dto.Trip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.exchangeBE.exchange.entity.Trip.Subject;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TravelTagDto {
    private Long id;
    private Long travelPostId;

    private CountryDto country; // CountryDto를 추가
    //private String placeName;  // PlaceName을 문자열로 추가
    // 주제 필드를 Enum으로 변경
    private Subject subject;
    private LocalDateTime travelDateStart;
    private LocalDateTime travelDateEnd;

    private PlaceDto place;     // PlaceDto 포함
}
