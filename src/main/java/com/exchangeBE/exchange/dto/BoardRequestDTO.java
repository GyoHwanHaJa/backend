package com.exchangeBE.exchange.dto;


import com.exchangeBE.exchange.entity.Community.BoardType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDTO {
    private String title;
    private String content;
    private BoardType boardType;
}
