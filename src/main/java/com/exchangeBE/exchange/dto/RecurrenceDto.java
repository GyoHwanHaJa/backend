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
        RecurrenceDto recurrenceDto = new RecurrenceDto();

        recurrenceDto.setId(recurrence.getId());
        recurrenceDto.setType(recurrence.getType());
        recurrenceDto.setDaysOfWeek(recurrence.getDaysOfWeek());
        recurrenceDto.setRecurrenceInterval(recurrence.getRecurrenceInterval());

        return recurrenceDto;
    }
}