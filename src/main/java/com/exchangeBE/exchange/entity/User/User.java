package com.exchangeBE.exchange.entity.User;

import com.exchangeBE.exchange.entity.Community.Board;
import com.exchangeBE.exchange.entity.Community.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "user")
    private Set<Board> boards;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}

