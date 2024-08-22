package com.exchangeBE.exchange.repository.report;

import com.exchangeBE.exchange.entity.Report.Report;
import com.exchangeBE.exchange.entity.Report.ReportType;
import com.exchangeBE.exchange.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByUser(User user);
    List<Report> findByUserAndReportType(User user, ReportType reportType);
}
