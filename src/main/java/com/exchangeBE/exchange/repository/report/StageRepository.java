package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Long> {
}
