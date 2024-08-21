package com.exchangeBE.exchange.dto.report;

import lombok.Data;

import java.util.List;

@Data
public class StageResponseDto {
    private String content;
    private List<String> options;
    private List<Integer> selectedOptions;
    private List<String> imageUrls;
}