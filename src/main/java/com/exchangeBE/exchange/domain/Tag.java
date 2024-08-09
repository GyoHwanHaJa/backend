package com.exchangeBE.exchange.domain;

import java.util.List;

public class Tag {

    private Long id;
    private String name;
    private List<TripPost> tripPosts; // TripPost 도메인 클래스와의 연관 관계

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

    public List<TripPost> getTripPosts() {
        return tripPosts;
    }

    public void setTripPosts(List<TripPost> tripPosts) {
        this.tripPosts = tripPosts;
    }
}
