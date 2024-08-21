package com.exchangeBE.exchange.entity.trip;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "travel_post")
public class TravelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //@Column(name = "user_id", nullable = false)
    //private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name = "page_view", nullable = false)
    private Integer pageView = 0;

    @Column(name = "likes", nullable = false)
    private Integer likes = 0;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TravelTagEntity> tags = new ArrayList<>(); // 필드 초기화

    @OneToMany(mappedBy = "travelPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>(); // 댓글 리스트

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void incrementPageView() {
        this.pageView += 1;
    }

    public void incrementLikes() {
        this.likes += 1;
    }

    public void decrementLikes() {
        if (this.likes > 0) {
            this.likes -= 1;
        }
    }

    /*public List<TravelTagEntity> getTags() {
        return tags != null ? tags : Collections.emptyList();
    }*/


    // 다른 필드와 메서드...


    public void addComment(CommentEntity comment) {
        this.comments.add(comment);
        comment.setTravelPost(this); // 양방향 관계 설정
    }


    public void addTag(TravelTagEntity tag) {
        this.tags.add(tag);
        tag.setTravel(this); // 양방향 관계 설정
    }

    // Add a method to add a tag
    /*public void addTag(TravelTagEntity tag) {
        this.tags.add(tag);
        tag.setTravel(this); // Ensure the bidirectional relationship is maintained
    }*/
}
