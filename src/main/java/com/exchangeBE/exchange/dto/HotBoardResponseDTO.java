package com.exchangeBE.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotBoardResponseDTO {
    private Long id;
    private String title;
    private String username;
    private Integer views;
    private Integer scrap;
    private Integer hotScore; // Hot 점수
}