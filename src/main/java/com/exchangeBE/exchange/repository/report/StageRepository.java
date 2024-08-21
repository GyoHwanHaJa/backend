package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.ReportType;
import com.exchangeBE.exchange.entity.Report.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByReportTypeOrderByStageOrder(ReportType reportType);
}