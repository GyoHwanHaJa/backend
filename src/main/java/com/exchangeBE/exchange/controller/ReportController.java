package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.*;
import com.exchangeBE.exchange.dto.report.ReportTypeDto;
import com.exchangeBE.exchange.dto.report.StageDto;

import com.exchangeBE.exchange.dto.report.StageRequestDto;
import com.exchangeBE.exchange.dto.report.StageResponseDto;
import com.exchangeBE.exchange.service.report.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
@Tag(name = "보고서 API", description = "여행 기록 보고서 관련 기능 API")
public class ReportController {
    private final ReportService reportService;

    @Operation(summary = "보고서 생성", description = "새로운 보고서를 생성합니다.")
    @ApiResponse(responseCode = "201", description = "보고서 생성 성공",
            content = @Content(schema = @Schema(implementation = Long.class)))
    @PostMapping("/{userId}")
    public ResponseEntity createReport(@PathVariable Long userId) {
        Long reportId = reportService.createReport(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(reportId);
    }

    @Operation(summary = "보고서 유형 설정", description = "보고서의 유형을 설정합니다.")
    @ApiResponse(responseCode = "201", description = "보고서 유형 설정 성공",
            content = @Content(schema = @Schema(implementation = Long.class)))
    @PatchMapping("/type")
    public ResponseEntity setReportType(@RequestBody ReportTypeDto reportTypeDto) {
        Long reportId = reportService.setReportType(reportTypeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(reportId);
    }

    @Operation(summary = "단계 정보 조회", description = "보고서의 특정 단계 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "특정 단계 정보 조회 성공",
            content = @Content(schema = @Schema(implementation = StageResponseDto.class)))
    @GetMapping("/stage")
    public ResponseEntity getStage(@RequestBody StageDto stageDto) {
        StageResponseDto stageResponseDto = reportService.getStage(stageDto);
        return ResponseEntity.status(HttpStatus.OK).body(stageResponseDto);
    }

    @Operation(summary = "단계 정보 설정", description = "보고서의 특정 단계 정보를 설정합니다.")
    @ApiResponse(responseCode = "200", description = "특정 단계 정보 설정 성공")
    @PutMapping("/stage")
    public ResponseEntity setStage(@RequestBody StageRequestDto stageRequestDto) throws IOException {
        reportService.setStage(stageRequestDto);
        return null;
    }

    @GetMapping("/report/{reportId}")
    @Operation(summary = "모든 단계 정보 조회", description = "특정 보고서의 모든 단계 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "모든 단계 정보 조회 성공",
            content = @Content(schema = @Schema(implementation = StageResponseDto.class)))
    public ResponseEntity getAllStages(@PathVariable Long reportId) {
        List<StageResponseDto> stageResponseDtoList = reportService.getAllStages(reportId);
        return ResponseEntity.status(HttpStatus.OK).body(stageResponseDtoList);
    }

    @PutMapping
    @Operation(summary = "모든 단계 정보 설정", description = "특정 보고서의 모든 단계 정보를 설정합니다.")
    @ApiResponse(responseCode = "200", description = "모든 단계 정보 설정 성공")
    public ResponseEntity setAllStages(@RequestBody List<StageRequestDto> stageRequestDtoList) throws IOException {
        reportService.setAllStages(stageRequestDtoList);
        return null;
    }
}