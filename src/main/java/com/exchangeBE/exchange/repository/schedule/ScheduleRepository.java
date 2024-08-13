package com.exchangeBE.exchange.repository.schedule;

import com.exchangeBE.exchange.entity.Schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Integer countByUserId(long userId);

    @Query("SELECT r FROM Schedule r WHERE YEAR(r.startTime) = :year AND MONTH(r.startTime) = :month")
    List<Schedule> findAllByYearAndMonth(int year, int month);

    List<Schedule> findAllByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
