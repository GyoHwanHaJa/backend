package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.OptionDto;
import com.exchangeBE.exchange.dto.StageDto;
import com.exchangeBE.exchange.entity.Report.Options;
import com.exchangeBE.exchange.entity.Report.Report;
import com.exchangeBE.exchange.entity.Report.Stage;
import com.exchangeBE.exchange.repository.report.OptionRepository;
import com.exchangeBE.exchange.repository.report.ReportRepository;
import com.exchangeBE.exchange.repository.report.StageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UploadDataService {
    private final StageRepository stageRepository;
    private final ReportRepository reportRepository;
    private final OptionRepository optionRepository;

    public UploadDataService(StageRepository stageRepository, ReportRepository reportRepository, OptionRepository optionRepository) {
        this.stageRepository = stageRepository;
        this.reportRepository = reportRepository;
        this.optionRepository = optionRepository;
    }

    @Transactional
    public Stage createStage(StageDto stageDto) {
        Report report = reportRepository.findByType(stageDto.getType())
                .orElseGet(() -> {
                    Report newReport = new Report();
                    newReport.setType(stageDto.getType());
                    return reportRepository.save(newReport);
                });

        // 이미 같은 번호의 Stage가 있는지 확인
        if (report.getStages().stream().anyMatch(s -> s.getStageNumber().equals(stageDto.getStageNumber()))) {
            throw new IllegalArgumentException("Stage with number " + stageDto.getStageNumber() + " already exists for this report.");
        }

        Stage stage = new Stage();
        stage.setStageNumber(stageDto.getStageNumber());
        stage.setContent(stageDto.getContent());
        stage.setReport(report);

        stage = stageRepository.save(stage);

        return stage;
    }


    public Options createOption(OptionDto optionDto) {
        Stage stage = stageRepository.findById(optionDto.getStageId()).get();

        Options option = new Options();
        option.setStage(stage);
        option.setContent(optionDto.getContent());

        option = optionRepository.save(option);

        return option;
    }
}
