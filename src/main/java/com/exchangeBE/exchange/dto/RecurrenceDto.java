package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.Schedule.Recurrence;
import com.exchangeBE.exchange.entity.Schedule.RecurrenceType;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.Set;

@Getter
@Setter
public class RecurrenceDto {
    private Long id;
    private RecurrenceType type;
    private Set<DayOfWeek> daysOfWeek;
    private Integer RecurrenceInterval;

    public static RecurrenceDto toRecurrenceDto(Recurrence recurrence) {
        RecurrenceDto dto = new RecurrenceDto();

        dto.setId(recurrence.getId());
        dto.setType(recurrence.getType());
        dto.setDaysOfWeek(recurrence.getDaysOfWeek());
        dto.setRecurrenceInterval(recurrence.getRecurrenceInterval());

        return dto;
    }
}