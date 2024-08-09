package com.exchangeBE.exchange.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ScheduleRequestDto {
    private ScheduleDto scheduleDto;
    private RecurrenceDto recurrenceDto;
    private Set<TagDto> tagDto;
}
