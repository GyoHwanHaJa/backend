package com.exchangeBE.exchange.dto.schedule;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordRequestDto {
    private Long userId;
    private LocalDate requestDate;
}
