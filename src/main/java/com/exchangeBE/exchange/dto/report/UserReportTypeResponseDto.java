package com.exchangeBE.exchange.dto.report;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserReportTypeResponseDto {
    private Long reportId;
    private String reportTitle;
    private LocalDate createdDate;
}
