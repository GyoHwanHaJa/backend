package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.TravelEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TravelTagDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "travel_id", nullable = false)
    private TravelEntity travel;

    // subject 필드 추가: 주제와 관련된 태그
    @Column(nullable = false)
    private String subject; // 주제 태그 ("cost-effectiveness", "local", "must-eat restaurant", "history")

    // 여행 날짜 태그들
    @Column(name = "travel_date_start")
    private String travelDateStart;

    @Column(name = "travel_date_end")
    private String travelDateEnd;

    private CountryDto country; // CountryDto를 추가
    //private String placeName;  // PlaceName을 문자열로 추가
}



//package com.exchangeBE.exchange.dto;
//
//import com.exchangeBE.exchange.dto.CountryDto;
//import com.exchangeBE.exchange.dto.PlaceDto;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.time.LocalDateTime;
//
//@Getter
//@Setter
//@ToString
//public class TravelTagDto {
//    private Long id;
//    private Long travelPostId;
//
//    private CountryDto country; // CountryDto를 추가
//    //private String placeName;  // PlaceName을 문자열로 추가
//    private String subject;
//    private LocalDateTime travelDateStart;
//    private LocalDateTime travelDateEnd;
//
//    private PlaceDto place;     // PlaceDto 포함
//}


