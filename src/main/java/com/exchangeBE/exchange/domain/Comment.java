package com.exchangeBE.exchange.domain;

import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "trippost_id")
    private TripPost tripPost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
}
