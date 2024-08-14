package com.exchangeBE.exchange.entity;

import com.exchangeBE.exchange.dto.CommentRequestDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    private List<Likes> likesList = new ArrayList<>();

    @Column
    private Long parentCommentId;

    @OrderBy("createdAt asc ")
    @OneToMany(mappedBy = "parentCommentId", cascade = CascadeType.ALL)
    private List<Comment> childCommentList = new ArrayList<>();

    @Builder
    private Comment(CommentRequestDTO requestDto, Board board, User user) {
        this.content = requestDto.getContents();
        this.parentCommentId = requestDto.getParentCommentId();
        this.board = board;
        this.user = user;
    }

    public void update(CommentRequestDTO requestDto, User user) {
        this.content = requestDto.getContents();
        this.user = user;
    }

    public static Comment of(CommentRequestDTO requestDto, Board board, User user) {
        Comment comment = Comment.builder()
                .requestDto(requestDto)
                .board(board)
                .user(user)
                .build();
        board.getCommentList().add(comment);
        return comment;
    }

    public void addChildComment(Comment child) {
        this.getChildCommentList().add(child);
    }
}