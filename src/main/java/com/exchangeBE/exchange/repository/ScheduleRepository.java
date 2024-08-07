package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.Schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
