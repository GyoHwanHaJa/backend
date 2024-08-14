package com.exchangeBE.exchange.entity.Schedule;

import com.exchangeBE.exchange.dto.ScheduleDto;
import com.exchangeBE.exchange.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String scheduleName;
    private String scheduleDescription;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)//, orphanRemoval = true)
    private Set<ScheduleTag> scheduleTags;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "recurrence_id", referencedColumnName = "id")
    private Recurrence recurrence;

    public static Schedule toScheduleEntity(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();

        schedule.setId(scheduleDto.getId());
        schedule.setUser(scheduleDto.getUser());
        schedule.setScheduleName(scheduleDto.getScheduleName());
        schedule.setScheduleDescription(scheduleDto.getScheduleDescription());
        schedule.setStartTime(scheduleDto.getStartTime());
        schedule.setEndTime(scheduleDto.getEndTime());
        schedule.setRecurrence(Recurrence.toRecurrenceEntity(scheduleDto.getRecurrenceDto()));
        schedule.setScheduleTags(scheduleDto.getScheduleTags());

        return schedule;
    }
}