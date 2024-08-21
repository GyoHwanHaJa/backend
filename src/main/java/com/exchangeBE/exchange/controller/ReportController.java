package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.report.ReportTypeDto;
import com.exchangeBE.exchange.dto.report.StageDto;
import com.exchangeBE.exchange.dto.report.StageRequestDto;
import com.exchangeBE.exchange.dto.report.StageResponseDto;
import com.exchangeBE.exchange.entity.Report.Report;
import com.exchangeBE.exchange.entity.Report.ReportStage;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
@Tag(name = "보고서", description = "여행 기록 보고서 API")
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/{userId}")
    @Operation(summary = "보고서 생성", description = "새로운 보고서를 생성합니다.")
    @ApiResponse(responseCode = "201", description = "보고서 생성 성공", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "400", description = "보고서 생성 실패 - 교환머니 잔액 부족", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "보고서 생성 실패 - 사용자를 찾을 수 없음", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity createReport(@PathVariable Long userId) {
        Long reportId = reportService.createReport(userId);

        Map<String, Long> response = new HashMap<>();
        response.put("reportId", reportId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/type")
    @Operation(summary = "보고서 유형 설정", description = "보고서의 유형을 설정합니다.")
    @ApiResponse(responseCode = "201", description = "보고서 유형 설정 성공", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "보고서 유형 설정 실패", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity setReportType(@RequestBody ReportTypeDto reportTypeDto) {
        Report report = reportService.setReportType(reportTypeDto);

        Map<String, Object> response = new HashMap<>();
        response.put("reportId", report.getId());
        response.put("reportType", report.getReportType().getDisplayName());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/stage")
    @Operation(summary = "단계 정보 조회", description = "보고서의 특정 단계 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "특정 단계 정보 조회 성공", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "특정 단계 정보 조회 실패", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity getStage(@RequestBody StageDto stageDto) {
        StageResponseDto stageResponseDto = reportService.getStage(stageDto);

        return ResponseEntity.status(HttpStatus.OK).body(stageResponseDto);
    }

    @PutMapping("/stage")
    @Operation(summary = "단계 정보 설정", description = "보고서의 특정 단계 정보를 설정합니다.")
    @ApiResponse(responseCode = "200", description = "특정 단계 정보 설정 성공", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "특정 단계 정보 설정 실패", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity setStage(@RequestBody StageRequestDto stageRequestDto) throws IOException {
        ReportStage reportStage = reportService.setStage(stageRequestDto);

        Map<String, Object> response = new HashMap<>();

        response.put("reportId", reportStage.getReport().getId());
        response.put("stageOrder", reportStage.getStage().getStageOrder());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/report/{reportId}")
    @Operation(summary = "모든 단계 정보 조회", description = "특정 보고서의 모든 단계 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "모든 단계 정보 조회 성공", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "모든 단계 정보 조회 실패", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity getAllStages(@PathVariable Long reportId) {
        List<StageResponseDto> stageResponseDtoList = reportService.getAllStages(reportId);
        return ResponseEntity.status(HttpStatus.OK).body(stageResponseDtoList);
    }

    @PutMapping
    @Operation(summary = "모든 단계 정보 설정", description = "특정 보고서의 모든 단계 정보를 설정합니다.")
    @ApiResponse(responseCode = "200", description = "모든 단계 정보 설정 성공", content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = "404", description = "모든 단계 정보 설정 실패", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity setAllStages(@RequestBody List<StageRequestDto> stageRequestDtoList) throws IOException {
        List<ReportStage> reportStageList = reportService.setAllStages(stageRequestDtoList);

        Map<String, Object> response = new HashMap<>();

        response.put("reportId", reportStageList.get(0).getReport().getId());

        for (ReportStage reportStage : reportStageList) {
            response.put("stageOrder", reportStage.getStage().getStageOrder());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}