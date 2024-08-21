package com.exchangeBE.exchange.dto.trip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private String domesticUniversity;
    private String domesticMajor;
    private String exchangeUniversity;
    private Integer exchangeMoney;
    private LocalDateTime exchangePeriodStart;
    private LocalDateTime exchangePeriodEnd;
    private String enrollmentCertificate;
    private Boolean approve;
    private String membershipLevel;
    private Integer totalView;
    private Integer totalScrap;
}
