package com.exchangeBE.exchange.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyDto {
    private Long id;
    private String content;
    private LocalDateTime timestamp;
    private Long commentId;

    // 기본 생성자
    public ReplyDto() {
    }

    // 모든 필드를 포함한 생성자
    public ReplyDto(Long id, String content, LocalDateTime timestamp, Long commentId) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.commentId = commentId;
    }
}
