package com.exchangeBE.exchange.repository.schedule;

import com.exchangeBE.exchange.entity.Schedule.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {
}
