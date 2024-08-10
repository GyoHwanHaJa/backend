package com.exchangeBE.exchange.dto;

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
    private String country;
    private String location;
    private String subject;
    private LocalDateTime travelDateStart;
    private LocalDateTime travelDateEnd;
}
