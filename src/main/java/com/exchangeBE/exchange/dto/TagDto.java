package com.exchangeBE.exchange.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class TagDto {

    private Long id;
    private String name;
    private String tagType;  // 태그 유형 (예: 주제, 날짜)
    private Boolean isCustom;  // 커스텀 태그 여부
    private LocalDate startDate;
    private LocalDate endDate;

    // 기본 생성자
    public TagDto() {
    }

    // 모든 필드를 포함한 생성자
    public TagDto(Long id, String name, String tagType, Boolean isCustom, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.tagType = tagType;
        this.isCustom = isCustom;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
