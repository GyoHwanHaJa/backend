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
    private String subject; // cost-effectiveness, local, must-eat, restaurant, history

    @Column(name = "travel_date_start", nullable = false)
    private LocalDateTime travelDateStart;

    @Column(name = "travel_date_end", nullable = false)
    private LocalDateTime travelDateEnd;

    // Country 엔티티와 다대일 관계 설정
    @Getter
    @ManyToOne
    @JoinColumn(name = "country_id")  // 외래 키 이름 설정
    private CountryEntity country;


    // Place 정보는 엔티티가 아니므로, 문자열로 저장
    @Column(name = "place_name")
    private String placeName;





}
