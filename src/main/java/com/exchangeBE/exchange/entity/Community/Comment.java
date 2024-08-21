package com.exchangeBE.exchange.entity.Community;

import com.exchangeBE.exchange.entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String nickname; // 댓글 작성자

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parentComment; //부모 댓글


    @CreatedDate
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    //. 일반적으로 @PrePersist 어노테이션을 사용하여 엔티티가 데이터베이스에 저장되기 전에 createdAt 필드를 설정할 수 있습니다.




}

