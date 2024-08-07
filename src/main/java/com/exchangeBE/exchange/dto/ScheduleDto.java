package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.Schedule.Schedule;
import com.exchangeBE.exchange.entity.Schedule.Users;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
public class ScheduleDto {
    private Long id;
    private Users user;
    private String scheduleName;
    private String scheduleDescription;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long recurrence_id;

    public static ScheduleDto toScheduleDto(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();

        scheduleDto.setId(schedule.getId());
        scheduleDto.setUser(schedule.getUser());
        scheduleDto.setScheduleName(schedule.getScheduleName());
        scheduleDto.setScheduleDescription(schedule.getScheduleDescription());
        scheduleDto.setStartTime(schedule.getStartTime());
        scheduleDto.setEndTime(schedule.getEndTime());

        return scheduleDto;
    }
}