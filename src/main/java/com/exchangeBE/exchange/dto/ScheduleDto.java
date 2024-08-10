package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.Schedule.Recurrence;
import com.exchangeBE.exchange.entity.Schedule.Schedule;
import com.exchangeBE.exchange.entity.Schedule.ScheduleTag;
import com.exchangeBE.exchange.entity.Schedule.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
public class ScheduleDto {
    private Long id;
    private User user;
    private String scheduleName;
    private String scheduleDescription;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private RecurrenceDto recurrenceDto;
    private Set<ScheduleTag> scheduleTags;

    public static ScheduleDto toScheduleDto(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();

        scheduleDto.setId(schedule.getId());
        scheduleDto.setUser(schedule.getUser());
        scheduleDto.setScheduleName(schedule.getScheduleName());
        scheduleDto.setScheduleDescription(schedule.getScheduleDescription());
        scheduleDto.setStartTime(schedule.getStartTime());
        scheduleDto.setEndTime(schedule.getEndTime());
        scheduleDto.setRecurrenceDto(RecurrenceDto.toRecurrenceDto(schedule.getRecurrence()));
        scheduleDto.setScheduleTags(schedule.getScheduleTags());

        return scheduleDto;
    }
}