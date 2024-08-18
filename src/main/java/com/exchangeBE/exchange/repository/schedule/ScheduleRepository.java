package com.exchangeBE.exchange.repository.schedule;

import com.exchangeBE.exchange.entity.Schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUserId(Long userId);
    List<Schedule> findByUserIdAndStartTimeBetween(Long userId, ZonedDateTime startDate, ZonedDateTime endDate);
}