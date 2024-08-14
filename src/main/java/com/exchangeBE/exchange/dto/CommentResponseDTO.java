package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDTO> childCommentList;

    @Builder
    private CommentResponseDTO(Comment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.username = entity.getUser().getUsername();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
        this.childCommentList = entity.getChildCommentList().stream().map(CommentResponseDTO::from).toList();
    }

    public static CommentResponseDTO from(Comment entity) {
        return CommentResponseDTO.builder()
                .entity(entity)
                .build();
    }

}