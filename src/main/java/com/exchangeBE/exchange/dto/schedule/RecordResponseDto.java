package com.exchangeBE.exchange.dto.schedule;

import lombok.Data;

import java.util.List;

@Data
public class RecordResponseDto {
    private Long leftDays;
    private Integer reportCount;
    List<Integer> scheduleDays;
    List<ScheduleInfoDto> scheduleInfo;
}
