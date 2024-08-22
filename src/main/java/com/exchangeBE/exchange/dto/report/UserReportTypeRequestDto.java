package com.exchangeBE.exchange.dto.report;

import com.exchangeBE.exchange.entity.Report.ReportType;
import lombok.Data;

@Data
public class UserReportTypeRequestDto {
    private Long userId;
    private ReportType reportType;
}
