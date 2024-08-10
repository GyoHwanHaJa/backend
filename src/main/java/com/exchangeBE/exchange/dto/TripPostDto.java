package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.TopicEntity;
import java.time.LocalDate;
import java.util.List;

public class TripPostDto {

    private Long id;
    private String title;
    private String description;
    private String country;  // 국가
    private LocalDate travelDateStart;  // 여행 시작 시간
    private LocalDate travelDateEnd;  // 여행 종료 시간
    private String location;  // 장소
    private List<String> tags;  // 태그 이름 목록
    private TopicEntity topic;  // 주제
    private List<String> photos;  // 이미지 파일 이름들

    // Getters 및 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getTravelDateStart() {
        return travelDateStart;
    }

    public void setTravelDateStart(LocalDate travelDateStart) {
        this.travelDateStart = travelDateStart;
    }

    public LocalDate getTravelDateEnd() {
        return travelDateEnd;
    }

    public void setTravelDateEnd(LocalDate travelDateEnd) {
        this.travelDateEnd = travelDateEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
