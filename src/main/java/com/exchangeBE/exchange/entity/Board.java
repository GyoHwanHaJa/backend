package com.exchangeBE.exchange.entity;

import com.exchangeBE.exchange.dto.BoardRequestDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "board")
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "hash_tag")
    private String hashTag;

    @Column(name = "content", nullable = false)
    private String content;

    @ColumnDefault("0")
    @Column(name = "view_count",nullable = false)
    private Integer viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Likes> likesList = new ArrayList<>();

    @Builder
    private Board(BoardRequestDTO requestsDto, User user) {
        this.title = requestsDto.getTitle();
        this.content = requestsDto.getContent();
        this.user = user;
    }

    public void update(BoardRequestDTO requestsDto, User user) {
        this.title = requestsDto.getTitle();
        this.content = requestsDto.getContent();
        this.user = user;
        this.hashTag = requestsDto.getHashTag();
    }

    public static Board of(BoardRequestDTO requestsDto, User user) {
        return Board.builder()
                .requestsDto(requestsDto)
                .user(user)
                .build();
    }

    public void viewCountUp(Board board) {
        board.viewCount++;
    }

}