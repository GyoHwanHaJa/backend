package com.exchangeBE.exchange.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private LocalDateTime timestamp;

    // 기본 생성자
    public CommentDto() {
    }

    // 모든 필드를 포함한 생성자
    public CommentDto(Long id, String content, LocalDateTime timestamp) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
    }
}
