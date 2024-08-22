package com.exchangeBE.exchange.dto.schedule;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateScheduleRequestDto {
    private Long userId;
    private LocalDate date;
}
