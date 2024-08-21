package com.exchangeBE.exchange.service.report;

import com.exchangeBE.exchange.dto.report.*;
import com.exchangeBE.exchange.entity.Report.*;

import com.exchangeBE.exchange.entity.User.User;
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
        User user = userRepository.findById(userId).get();

        if (user.getExchangeMoney() < 3000)
            return null;

        user.setExchangeMoney(user.getExchangeMoney() - 3000);

        Report report = new Report();

        report.setUser(user);
        report.setCreatedAt(LocalDateTime.now());

        return reportRepository.save(report).getId();
    }

    public Long setReportType(ReportTypeDto reportTypeDto) {
        Report report = reportRepository.findById(reportTypeDto.getReportId()).get();

        report.setReportType(reportTypeDto.getReportType());
        createReportStage(report); // -> stage와 option을 set

        return reportRepository.save(report).getId();
    }

    private void createReportStage(Report report) {
        reportStageService.createReportStage(report);
    }

    public StageResponseDto getStage(StageDto stageDto) {
        Report report = reportRepository.findById(stageDto.getReportId()).get();
        ReportStage reportStage = reportStageRepository.findByReport_IdAndStage_StageOrder(report.getId(), stageDto.getStageOrder()).get();
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
    public void setStage(StageRequestDto stageRequestDto) throws IOException {
        // 1. reportStage 가져온다.
        // 2. reportStageOption 생성
        // 3. reportStageImage 생성
        ReportStage reportStage = reportStageRepository.findByReport_IdAndStage_StageOrder(stageRequestDto.getReportId(), stageRequestDto.getStageOrder()).get();

        Stage stage = stageRepository.findById(reportStage.getStage().getId()).get();

        setReportStageOptions(reportStage, stage, stageRequestDto.getOptionOrders());
        setReportStageImages(reportStage, stageRequestDto.getBase64Images());
    }

    public void setAllStages(List<StageRequestDto> stageRequestDtoList) throws IOException {
        for(StageRequestDto stageRequestDto : stageRequestDtoList) {
            setStage(stageRequestDto);
        }
    }

    private byte[] decodeImages(String encodedImage) {
        return Base64.getDecoder().decode(encodedImage);
    }

    private void setReportStageOptions(ReportStage reportStage, Stage stage, List<Integer> optionOrders) {
        reportStage.getSelectedOptions().clear(); // 기존의 옵션 제거

        for (Integer optionOrder : optionOrders) {
            Option option = optionRepository.findByStageAndOptionOrder(stage, optionOrder).get();

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
}