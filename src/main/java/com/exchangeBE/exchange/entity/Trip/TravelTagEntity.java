package com.exchangeBE.exchange.entity.Trip;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "travel_tag")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id", nullable = false)
    private TravelEntity travel;

    // 주제 태그를 Enum으로 설정
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Subject subject; // 주제 태그 (예: "cost-effectiveness", "local", "must-eat restaurant", "history", "long-term-trip")

    // 여행 시작 날짜와 종료 날짜 태그
    @Column(name = "travel_date_start", nullable = true)
    private LocalDateTime travelDateStart; // LocalDateTime 타입

    @Column(name = "travel_date_end", nullable = true)
    private LocalDateTime travelDateEnd; // LocalDateTime 타입

// Country 엔티티와 다대일 관계 설정
//    @Getter
    @ManyToOne
    @JoinColumn(name = "country_id")  // 외래 키 이름 설정
    private CountryEntity country;


    // Place 정보는 엔티티가 아니므로, 문자열로 저장
    @Column(name = "place_name")
    private String placeName;

}
