package com.exchangeBE.exchange.entity;

import com.exchangeBE.exchange.entity.enumSet.UserRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String domesticUniversity;

    @Column(nullable = false)
    private String exchangeUniversity;

    @Column(nullable = false, length = 30)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;


    @Builder
    private User(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static User of(String username, String password, UserRoleEnum role) {
        return User.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();
    }

}