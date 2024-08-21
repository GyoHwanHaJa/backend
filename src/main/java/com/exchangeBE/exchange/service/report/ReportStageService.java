package com.exchangeBE.exchange.service.report;

import com.exchangeBE.exchange.entity.Report.Report;
import com.exchangeBE.exchange.entity.Report.ReportStage;
import com.exchangeBE.exchange.entity.Report.Stage;
import com.exchangeBE.exchange.exception.EntityNotFoundException;
import com.exchangeBE.exchange.repository.report.ReportStageRepository;
import com.exchangeBE.exchange.repository.report.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportStageService {
    private final StageRepository stageRepository;
    private final ReportStageRepository reportStageRepository;

    public void createReportStage(Report report) {

        List<Stage> stageList = Optional.ofNullable(stageRepository.findByReportTypeOrderByStageOrder(report.getReportType()))
                .orElseThrow(() -> new EntityNotFoundException(report.getReportType().getDisplayName() +"의 단계가 정의되어 있지 않습니다."));

        for(Stage stage : stageList) {
            ReportStage reportStage = new ReportStage();

            reportStage.setReport(report);
            reportStage.setStage(stage);

            reportStageRepository.save(reportStage);
        }
    }
}