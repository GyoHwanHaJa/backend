package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.Board;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String hashTag;
    private String username;
    private String content;
    private Integer likeCount;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDTO> commentResponseDTOList;

    @Builder
    private BoardResponseDTO(Board entity, List<CommentResponseDTO> list) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.username = entity.getUser().getUsername();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
        this.likeCount = entity.getLikesList() != null ? entity.getLikesList().size() : 0;
        this.commentResponseDTOList = list;
    }

    private BoardResponseDTO(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.username = entity.getUser().getUsername();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
        this.likeCount = entity.getLikesList() != null ? entity.getLikesList().size() : 0;
        this.commentResponseDTOList = entity.getCommentList().stream().map(CommentResponseDTO::from).toList();
    }

    public static BoardResponseDTO from(Board entity, List<CommentResponseDTO> list) {
        return BoardResponseDTO.builder()
                .entity(entity)
                .list(list)
                .build();
    }

    public static BoardResponseDTO from(Board entity) {
        return new BoardResponseDTO(entity);
    }
}
