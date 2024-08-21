package com.exchangeBE.exchange.dto.report;

import lombok.Data;

import java.util.List;

@Data
public class StageRequestDto {
    private Long reportId;
    private Integer stageOrder;
    private List<Integer> optionOrders;
    private List<Base64ImageDto> base64Images;
}