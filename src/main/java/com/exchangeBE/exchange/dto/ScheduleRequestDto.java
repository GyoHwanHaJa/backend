package com.exchangeBE.exchange.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private ScheduleDto scheduleDto;
    private RecurrenceDto recurrenceDto;
    private TagDto tagDto;
}
