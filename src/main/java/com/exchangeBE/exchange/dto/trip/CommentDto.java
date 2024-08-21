package com.exchangeBE.exchange.dto.trip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDto {
    private Long id;
    private String content;
    private Long userId;         // 추가된 부분
    private Long travelPostId;   // 추가된 부분
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
