package com.exchangeBE.exchange.entity;

import com.exchangeBE.exchange.entity.Schedule.MembershipLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String enrollmentCertificate; // url 들어와야 함
    private Boolean approve;
    private MembershipLevel membershipLevel;
    private Integer totalView;
    private Integer totalScrap;
}
