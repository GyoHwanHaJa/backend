package com.exchangeBE.exchange.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String domesticUniversity;
    private String exchangeUniversity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getDomesticUniversity() {
        return domesticUniversity;
    }

    public void setDomesticUniversity(String domesticUniversity) {
        this.domesticUniversity = domesticUniversity;
    }

    public String getExchangeUniversity() {
        return exchangeUniversity;
    }

    public void setExchangeUniversity(String exchangeUniversity) {
        this.exchangeUniversity = exchangeUniversity;
    }
}
