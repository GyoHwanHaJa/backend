package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.ReportStageImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportStageImageRepository extends JpaRepository<ReportStageImage, Long> {
}