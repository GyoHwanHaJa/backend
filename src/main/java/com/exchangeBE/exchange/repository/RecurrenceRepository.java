package com.exchangeBE.exchange.repository;

import com.exchangeBE.exchange.entity.Schedule.Recurrence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurrenceRepository extends JpaRepository<Recurrence, Long> {
}