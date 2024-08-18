package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.Schedule.RecurrenceType;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
public class ScheduleDTO {
    private Long id;
    private String scheduleName;
    private String scheduleDescription;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private Set<String> tagNames;
    private RecurrenceDTO recurrence;

    @Data
    public static class RecurrenceDTO {
        private RecurrenceType type;
        private Set<DayOfWeek> daysOfWeek;
        private Integer recurrenceInterval;
    }
}
