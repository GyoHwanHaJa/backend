package com.exchangeBE.exchange.entity;

import jakarta.persistence.*;
import lombok.*;

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

    // 주제 태그 설정
    @Column(nullable = true)  // 주제는 필수일 필요가 없을 수도 있음
    private String subject; // 예: "cost-effectiveness", "local", "must-eat restaurant", "history"

    // 여행 시작 날짜와 종료 날짜 태그
    @Column(name = "travel_date_start", nullable = true)
    private String travelDateStart;

    @Column(name = "travel_date_end", nullable = true)
    private String travelDateEnd;

// Country 엔티티와 다대일 관계 설정
    @Getter
    @ManyToOne
    @JoinColumn(name = "country_id")  // 외래 키 이름 설정
    private CountryEntity country;


    // Place 정보는 엔티티가 아니므로, 문자열로 저장
    @Column(name = "place_name")
    private String placeName;

}
