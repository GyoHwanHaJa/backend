package com.exchangeBE.exchange.dto;

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
    private Long userId;
    private Long travelPostId; // 수정된 부분 (tripPostId -> travelPostId)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
