package com.exchangeBE.exchange.dto.schedule;

import lombok.Data;

@Data
public class ScheduleInfoDto {
    private String scheduleName;
    private Integer hour;
    private Integer minute;
}
