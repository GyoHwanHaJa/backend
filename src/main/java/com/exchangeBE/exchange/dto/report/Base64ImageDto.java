package com.exchangeBE.exchange.dto.report;

import lombok.Data;

@Data
public class Base64ImageDto {
    private String base64Image;
    private String originalFilename;
}