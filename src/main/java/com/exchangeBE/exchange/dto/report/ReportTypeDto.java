package com.exchangeBE.exchange.dto.report;

import com.exchangeBE.exchange.entity.Report.ReportType;
import lombok.Data;

@Data
public class ReportTypeDto {
    private Long reportId;
    private ReportType reportType;
}