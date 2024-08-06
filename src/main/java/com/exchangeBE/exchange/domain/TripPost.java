package com.exchangeBE.exchange.domain;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.Date;
import java.util.List;

@Entity
public class TripPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 게시물의 고유 ID

    private String title; // 게시물의 제목
    private String content; // 게시물의 내용

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 게시물 작성자

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // 게시물 작성 날짜

    @ManyToMany
    @JoinTable(
            name = "trippost_tag",
            joinColumns = @JoinColumn(name = "trippost_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags; // 게시물에 관련된 태그 목록

    // Getters and Setters
    // 각 필드에 대한 getter와 setter 메서드를 정의합니다.
}
