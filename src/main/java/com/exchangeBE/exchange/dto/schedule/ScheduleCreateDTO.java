package com.exchangeBE.exchange.dto.schedule;

import lombok.Data;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
public class ScheduleCreateDTO {
    private Long userId;
    private Long scheduleId;
    private String scheduleName;
    private String scheduleDescription;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private Set<String> tagNames;
    private RecurrenceCreateDTO recurrence;
}