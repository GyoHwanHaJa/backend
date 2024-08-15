package com.exchangeBE.exchange.dto;

import com.exchangeBE.exchange.entity.Report.ReportType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StageDto {
    private ReportType type;
    private Integer stageNumber;
    private String content;
}
