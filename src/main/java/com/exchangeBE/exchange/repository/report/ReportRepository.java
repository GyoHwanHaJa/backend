package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.Report;
import com.exchangeBE.exchange.entity.Report.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByType(ReportType type);
}
