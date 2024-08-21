package com.exchangeBE.exchange.dto.Trip;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountryDto {

    //private Long id;
    //private String region;
    private String name;

    // 기본 생성자
    public CountryDto() {}

    // 필드를 모두 사용하는 생성자
    public CountryDto(String name) {
        //this.id = id;
        //this.region = region;
        this.name = name;
    }
}
