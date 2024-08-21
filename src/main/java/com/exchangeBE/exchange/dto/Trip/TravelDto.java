package com.exchangeBE.exchange.dto.Trip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class TravelDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private Integer pageView;
    private Integer likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 추가된 부분: 태그 정보를 포함하는 리스트
    private List<TravelTagDto> tags;
}
