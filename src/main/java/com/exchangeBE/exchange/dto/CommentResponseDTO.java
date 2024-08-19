package com.exchangeBE.exchange.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private Long parentId; // 대댓글의 경우 부모 댓글 ID
    private List<CommentResponseDTO> replies; // 대댓글 리스트

    public CommentResponseDTO(Long id, String content, String username, LocalDateTime createdAt, Long parentId, List<CommentResponseDTO> replies) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
        this.parentId = parentId;
        this.replies = replies;
    }
}