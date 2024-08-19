package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.BoardType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private BoardType boardType;
    private Integer likes; // 좋아요 수
    private Integer views; // 조회수
    private Integer scrap; // 스크랩수
}

