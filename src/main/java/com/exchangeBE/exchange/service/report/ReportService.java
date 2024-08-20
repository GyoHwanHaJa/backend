package com.exchangeBE.exchange.service.report;

import com.exchangeBE.exchange.entity.Report.*;
import com.exchangeBE.exchange.entity.User.User;
import com.exchangeBE.exchange.repository.Community.UserRepository;
import com.exchangeBE.exchange.repository.report.OptionRepository;
import com.exchangeBE.exchange.repository.report.ReportRepository;
import com.exchangeBE.exchange.repository.report.UserAnswerOptionRepository;
import com.exchangeBE.exchange.repository.report.UserAnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final UserRepository userRepository;
    private final ReportRepository reportRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final OptionRepository optionRepository;
    private final UserAnswerOptionRepository userAnswerOptionRepository;

    public ReportService(UserRepository userRepository, ReportRepository reportRepository, UserAnswerRepository userAnswerRepository, OptionRepository optionRepository, UserAnswerOptionRepository userAnswerOptionRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
        this.userAnswerRepository = userAnswerRepository;
        this.optionRepository = optionRepository;
        this.userAnswerOptionRepository = userAnswerOptionRepository;
    }

    public Report createReport(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        Report report = new Report();
        // 교환 머니 3000 마이너스 해야 함
        report.setUser(user);
        return reportRepository.save(report);
    }

    public UserAnswer saveUserAnswer(Long reportId, int stageNumber, List<Long> selectedOptionIds) {
        Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        Stage stage = report.getStages().stream()
                .filter(s -> s.getStageNumber() == stageNumber)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUser(report.getUser());
        userAnswer.setStage(stage);

        userAnswer = userAnswerRepository.save(userAnswer);

        for (Long optionId : selectedOptionIds) {
            Options option = optionRepository.findById(optionId).orElseThrow(() -> new RuntimeException("Option not found"));
            UserAnswerOption userAnswerOption = new UserAnswerOption();
            userAnswerOption.setUserAnswer(userAnswer);
            userAnswerOption.setOption(option);
            userAnswerOptionRepository.save(userAnswerOption);
        }

        return userAnswer;
    }
}
