package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
