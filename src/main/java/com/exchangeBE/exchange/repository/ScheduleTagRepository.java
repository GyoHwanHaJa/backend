package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.Schedule.Schedule;
import com.exchangeBE.exchange.entity.Schedule.ScheduleTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleTagRepository extends JpaRepository<ScheduleTag, Long> {
    ScheduleTag findBySchedule(Schedule schedule);
}
