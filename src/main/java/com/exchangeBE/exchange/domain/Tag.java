package com.exchangeBE.exchange.domain;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ManyToMany(mappedBy = "tags")
    private List<TripPost> tripPosts;
    private User user;

    public User getUser() {
        this.user = user;
        return null;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // Getters and Setters
}
