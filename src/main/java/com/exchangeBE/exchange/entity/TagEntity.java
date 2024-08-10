package com.exchangeBE.exchange.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // 태그 이름
    private String tagType;  // 태그 유형 (예: 주제, 날짜)
    private Boolean isCustom;  // 커스텀 태그 여부

    @ManyToMany(mappedBy = "tags")
    private Set<TripPostEntity> tripPosts = new HashSet<>();

//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getTagType() {
//        return tagType;
//    }
//
//    public void setTagType(String tagType) {
//        this.tagType = tagType;
//    }
//
//    public Boolean getIsCustom() {
//        return isCustom;
//    }
//
//    public void setIsCustom(Boolean isCustom) {
//        this.isCustom = isCustom;
//    }
//
//    public Set<TripPostEntity> getTripPosts() {
//        return tripPosts;
//    }
//
//    public void setTripPosts(Set<TripPostEntity> tripPosts) {
//        this.tripPosts = tripPosts;
//    }
}
