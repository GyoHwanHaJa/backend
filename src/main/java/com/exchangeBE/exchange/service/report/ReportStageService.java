package com.exchangeBE.exchange.service.report;

import com.exchangeBE.exchange.entity.Report.Report;
import com.exchangeBE.exchange.entity.Report.ReportStage;
import com.exchangeBE.exchange.entity.Report.Stage;
import com.exchangeBE.exchange.repository.report.ReportStageRepository;
import com.exchangeBE.exchange.repository.report.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportStageService {
    private final StageRepository stageRepository;
    private final ReportStageRepository reportStageRepository;

    public void createReportStage(Report report) {
        // stage부터 가져와야겠다.

        List<Stage> stageList = stageRepository.findByReportTypeOrderByStageOrder(report.getReportType());

        for(Stage stage : stageList) {
            ReportStage reportStage = new ReportStage();
            reportStage.setReport(report);
            reportStage.setStage(stage);

            reportStageRepository.save(reportStage);
        }
    }
}