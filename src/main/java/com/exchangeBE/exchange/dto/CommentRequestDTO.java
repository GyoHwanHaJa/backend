package com.exchangeBE.exchange.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDTO {
    private String content;
    private Long parentId; // 대댓글의 경우 부모 댓글 ID
    private String username; // 사용자 이름
}
