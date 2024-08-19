package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.entity.Report.Report;
import com.exchangeBE.exchange.service.report.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    //@GetMapping("/{userId}") // list
    //@GetMapping("/{reportId}")
    //@PostMapping("/{userId}")

    public ResponseEntity<?> createReport(@PathVariable Long userId) {
        Report report = reportService.createReport(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }
}
