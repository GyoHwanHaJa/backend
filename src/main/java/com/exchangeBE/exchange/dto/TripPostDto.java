package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.TopicEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class TripPostDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> tags; // 태그 이름 목록
    private TopicEntity topic;
    private List<String> photos; // 이미지 파일 이름들

    // Getters and Setters
    // ... (생략 가능)
}
