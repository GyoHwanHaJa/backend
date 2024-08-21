package com.exchangeBE.exchange.dto.schedule;

import com.exchangeBE.exchange.entity.Schedule.RecurrenceType;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
public class RecurrenceCreateDTO {
    private RecurrenceType type;
    private Set<DayOfWeek> daysOfWeek;
    private Integer recurrenceInterval;
    private ZonedDateTime recurrenceEndDate;
}
