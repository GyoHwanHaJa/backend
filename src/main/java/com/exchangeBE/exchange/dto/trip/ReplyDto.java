package com.exchangeBE.exchange.dto.trip;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReplyDto {
    private Long id;
    private String content;
    private Long commentId; // 댓글의 ID
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
