package com.exchangeBE.exchange.repository.schedule;

import com.exchangeBE.exchange.entity.Schedule.Schedule;
import com.exchangeBE.exchange.entity.Schedule.ScheduleTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTagRepository extends JpaRepository<ScheduleTag, Long> {
    ScheduleTag findBySchedule(Schedule schedule);
}
