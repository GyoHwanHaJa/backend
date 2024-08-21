package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.ReportStage;
import com.exchangeBE.exchange.entity.Report.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportStageRepository extends JpaRepository<ReportStage, Long> {
    Optional<ReportStage> findByReport_IdAndStage_StageOrder(Long reportId, Integer stageOrder);
}
