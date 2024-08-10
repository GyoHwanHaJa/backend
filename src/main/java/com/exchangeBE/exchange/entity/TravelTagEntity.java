package com.exchangeBE.exchange.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "travel_tag")
public class TravelTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_post_id", nullable = false)
    private TravelEntity travelPost;

    @Column(nullable = false)
    private String country;

    @Column
    private String location;

    @Column(nullable = false)
    private String subject; // cost-effectiveness, local, must-eat, restaurant, history

    @Column(name = "travel_date_start", nullable = false)
    private LocalDateTime travelDateStart;

    @Column(name = "travel_date_end", nullable = false)
    private LocalDateTime travelDateEnd;
}
