package com.exchangeBE.exchange.dto;

import lombok.Getter;

@Getter
public class CommentRequestDTO {
    String contents;
    Long parentCommentId;
}
