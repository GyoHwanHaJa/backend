package com.exchangeBE.exchange.service.report;

import com.exchangeBE.exchange.dto.report.*;
import com.exchangeBE.exchange.entity.Report.*;

import com.exchangeBE.exchange.entity.User.User;
import com.exchangeBE.exchange.exception.EntityNotFoundException;
import com.exchangeBE.exchange.exception.InsufficientExchangeMoneyException;
import com.exchangeBE.exchange.repository.Community.UserRepository;
import com.exchangeBE.exchange.repository.report.*;
import com.exchangeBE.exchange.service.image.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final StageRepository stageRepository;
    private final UserRepository userRepository;
    private final ReportStageService reportStageService;
    private final ReportStageRepository reportStageRepository;
    private final OptionRepository optionRepository;
    private final S3Service s3Service;

    public Long createReport(Long userId) {
        // 유저 검색
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + userId + "인 사용자를 찾을 수 없습니다."));

        // 교환머니 충분한지 확인
        if (user.getExchangeMoney() < 3000) {
            throw new InsufficientExchangeMoneyException("보고서 생성을 위한 교환머니가 부족합니다.");
        }

        // 교환머니 차감
        user.setExchangeMoney(user.getExchangeMoney() - 3000);

        // 새로운 보고서 생성
        Report report = new Report();
        report.setUser(user);
        report.setCreatedAt(LocalDateTime.now());

        return reportRepository.save(report).getId();
    }

    public Report setReportType(ReportTypeDto reportTypeDto) {
        Report report = reportRepository.findById(reportTypeDto.getReportId())
                        .orElseThrow(() -> new EntityNotFoundException("ID가 " + reportTypeDto.getReportId() + "인 보고서를 찾을 수 없습니다."));

        report.setReportType(reportTypeDto.getReportType());
        createReportStage(report); // -> stage와 option을 set

        return reportRepository.save(report);
    }

    private void createReportStage(Report report) {
        reportStageService.createReportStage(report);
    }

    public StageResponseDto getStage(StageDto stageDto) {
        Report report = reportRepository.findById(stageDto.getReportId())
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + stageDto.getReportId() + "인 보고서를 찾을 수 없습니다."));

        ReportStage reportStage = reportStageRepository.findByReport_IdAndStage_StageOrder(report.getId(), stageDto.getStageOrder())
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + report.getId() + "인 보고서의 " + stageDto.getStageOrder() + " 단계의 연결을 찾을 수 없습니다."));
        Stage stage = reportStage.getStage(); // 얘 필요

        List<Option> optionList = stage.getOptions(); // 얘 필요

        List<ReportStageImage> imageList = reportStage.getImages();
        List<ReportStageOption> selectedOptionList = reportStage.getSelectedOptions();

        StageResponseDto stageResponseDto = new StageResponseDto();
        stageResponseDto.setContent(stage.getContent());

        stageResponseDto.setOptions(
                optionList.stream()
                        .map(Option::getContent)
                        .collect(Collectors.toList())

        );

        stageResponseDto.setImageUrls(
                imageList.stream()
                        .map(ReportStageImage::getImageUrl)
                        .collect(Collectors.toList())
        );

        stageResponseDto.setSelectedOptions(
                selectedOptionList.stream()
                        .map(ReportStageOption::getOption)
                        .map(Option::getOptionOrder)
                        .collect(Collectors.toList())
        );

        return stageResponseDto;
    }

    @Transactional
    public ReportStage setStage(StageRequestDto stageRequestDto) throws IOException {
        ReportStage reportStage = reportStageRepository.findByReport_IdAndStage_StageOrder(stageRequestDto.getReportId(), stageRequestDto.getStageOrder())
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + stageRequestDto.getReportId() + "인 보고서의 " + stageRequestDto.getStageOrder() + " 단계의 연결을 찾을 수 없습니다."));

        Stage stage = stageRepository.findById(reportStage.getStage().getId())
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + stageRequestDto.getReportId() + "인 보고서의 " + stageRequestDto.getStageOrder() + " 단계를 찾을 수 없습니다."));;

        setReportStageOptions(reportStage, stage, stageRequestDto.getOptionOrders());
        setReportStageImages(reportStage, stageRequestDto.getBase64Images());

        return reportStage;
    }

    public List<ReportStage> setAllStages(List<StageRequestDto> stageRequestDtoList) throws IOException {
        List<ReportStage> reportStageList = new ArrayList<>();

        for(StageRequestDto stageRequestDto : stageRequestDtoList) {
            reportStageList.add(setStage(stageRequestDto));
        }

        return reportStageList;
    }

    private byte[] decodeImages(String encodedImage) {
        return Base64.getDecoder().decode(encodedImage);
    }

    private void setReportStageOptions(ReportStage reportStage, Stage stage, List<Integer> optionOrders) {
        reportStage.getSelectedOptions().clear(); // 기존의 옵션 제거

        for (Integer optionOrder : optionOrders) {
            Option option = optionRepository.findByStageAndOptionOrder(stage, optionOrder)
                    .orElseThrow(() -> new EntityNotFoundException("ID가 " + stage.getId() + "인 단계의 " + optionOrder + "번 옵션을 찾을 수 없습니다."));

            ReportStageOption reportStageOption = new ReportStageOption();
            reportStageOption.setReportStage(reportStage);
            reportStageOption.setOption(option);

            reportStage.getSelectedOptions().add(reportStageOption);
        }
    }

    private void setReportStageImages(ReportStage reportStage, List<Base64ImageDto> base64Images) throws IOException {

        for(ReportStageImage reportStageImage : reportStage.getImages()) {
            s3Service.deleteImage(reportStageImage.getImageUrl());
        }

        reportStage.getImages().clear(); // 기존 이미지 삭제, S3에서 삭제하는 기능 추가해야함

        AtomicInteger index = new AtomicInteger(1);
        for (Base64ImageDto base64ImageDto : base64Images) {
            byte[] image = decodeImages(base64ImageDto.getBase64Image());
            String imageUrl = s3Service.upload(image, base64ImageDto.getOriginalFilename());

            ReportStageImage reportStageImage = new ReportStageImage();
            reportStageImage.setReportStage(reportStage);
            reportStageImage.setImageUrl(imageUrl);
            reportStageImage.setImageOrder(index.getAndIncrement());

            reportStage.getImages().add(reportStageImage);
        }
    }

    public List<StageResponseDto> getAllStages(Long reportId) {
        List<StageResponseDto> stageResponseDtoList = new ArrayList<>();

        for (int i = 1; i < 7; i++) {
            StageDto stageDto = new StageDto();

            stageDto.setReportId(reportId);
            stageDto.setStageOrder(i);

            StageResponseDto stageResponseDto = getStage(stageDto);
            stageResponseDtoList.add(stageResponseDto);
        }

        return stageResponseDtoList;
    }

    public void deleteStage(Long reportId) {
        reportRepository.deleteById(reportId);
    }

    public Integer countReports(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + userId + "인 사용자를 찾을 수 없습니다."));

        List<Report> reportList = reportRepository.findByUser(user);

        return reportList.size();
    }

    public Report setReportName(ReportTitleDto reportTitleDto) {
        Report report = reportRepository.findById(reportTitleDto.getReportId())
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + reportTitleDto.getReportId() + "인 보고서를 찾을 수 없습니다."));

        report.setTitle(reportTitleDto.getReportTitle());

        return reportRepository.save(report);
    }

    public List<UserReportTypeResponseDto> getReportByType(UserReportTypeRequestDto userReportTypeRequestDto) {
        User user = userRepository.findById(userReportTypeRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + userReportTypeRequestDto.getUserId() + "인 사용자를 찾을 수 없습니다."));

        List<Report> reportList = reportRepository.findByUserAndReportType(user, userReportTypeRequestDto.getReportType());

        List<UserReportTypeResponseDto> userReportTypeResponseDtoList = new ArrayList<>();

        for (Report report : reportList) {
            UserReportTypeResponseDto userReportTypeResponseDto = new UserReportTypeResponseDto();
            userReportTypeResponseDto.setReportId(report.getId());
            userReportTypeResponseDto.setReportTitle(report.getTitle());
            userReportTypeResponseDto.setCreatedDate(report.getCreatedAt().toLocalDate());

            userReportTypeResponseDtoList.add(userReportTypeResponseDto);
        }

        return userReportTypeResponseDtoList;


    }
}