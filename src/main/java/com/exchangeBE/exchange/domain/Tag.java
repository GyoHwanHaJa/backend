package com.exchangeBE.exchange.domain;

import java.util.List;

public class Tag {

    private Long id;
    private String name;  // 태그 이름
    private String tagType;  // 태그 유형 (예: 주제, 날짜)
    private Boolean isCustom;  // 커스텀 태그 여부
    private List<TripPost> tripPosts;  // TripPost 클래스와의 연관 관계

    // Getter 및 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public Boolean getIsCustom() {
        return isCustom;
    }

    public void setIsCustom(Boolean isCustom) {
        this.isCustom = isCustom;
    }

    public List<TripPost> getTripPosts() {
        return tripPosts;
    }

    public void setTripPosts(List<TripPost> tripPosts) {
        this.tripPosts = tripPosts;
    }
}
