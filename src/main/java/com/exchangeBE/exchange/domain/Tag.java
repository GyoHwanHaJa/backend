package com.exchangeBE.exchange.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags") // TripPost 클래스의 필드 이름과 일치시켜야 합니다.
    private List<TripPost> tripPosts;

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
