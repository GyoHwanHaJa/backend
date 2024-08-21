package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.ReportStageOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportStageOptionRepository extends JpaRepository<ReportStageOption, Long> {
}
