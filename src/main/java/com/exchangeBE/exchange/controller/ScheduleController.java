package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.schedule.ScheduleCreateDTO;
import com.exchangeBE.exchange.dto.schedule.ScheduleDTO;
import com.exchangeBE.exchange.entity.Schedule.Schedule;

import com.exchangeBE.exchange.service.schedule.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
@Tag(name = "일정", description = "일정 관리 API")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    @Operation(summary = "새 일정 생성",
            description = "제공된 데이터를 기반으로 새 일정을 생성합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "일정이 성공적으로 생성됨",
                            content = @Content(schema = @Schema(implementation = Schedule.class))),
                    @ApiResponse(responseCode = "400", description = "잘못된 입력")
            })
    public ResponseEntity createSchedule(@RequestBody ScheduleCreateDTO dto) {
        try {
            Long scheduleId = scheduleService.createOrUpdateSchedule(dto);

            Map<String, Long> response = new HashMap<>();
            response.put("scheduleId", scheduleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "일정 생성에 실패했습니다.");
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{scheduleId}")
    @Operation(summary = "기존 일정 수정",
            description = "지정된 ID의 일정을 수정합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "일정이 성공적으로 수정됨",
                            content = @Content(schema = @Schema(implementation = Schedule.class))),
                    @ApiResponse(responseCode = "404", description = "일정을 찾을 수 없음")
            })
    public ResponseEntity updateSchedule(
            @RequestBody ScheduleCreateDTO dto,
            @Parameter(description = "수정할 일정의 ID") @PathVariable Long scheduleId) {
        try {
            dto.setScheduleId(scheduleId);
            scheduleId = scheduleService.createOrUpdateSchedule(dto);

            Map<String, Long> response = new HashMap<>();
            response.put("scheduleId", scheduleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "일정 수정에 실패했습니다.");
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(summary = "일정 삭제",
            description = "지정된 ID의 일정을 삭제합니다",
            responses = {
                    @ApiResponse(responseCode = "204", description = "일정이 성공적으로 삭제됨"),
                    @ApiResponse(responseCode = "404", description = "일정을 찾을 수 없음")
            })
    public void deleteSchedule(
            @Parameter(description = "삭제할 일정의 ID") @PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }

    @GetMapping("/{scheduleId}")
    @Operation(summary = "ID로 일정 조회",
            description = "지정된 ID의 일정을 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공적인 조회",
                            content = @Content(schema = @Schema(implementation = ScheduleDTO.class))),
                    @ApiResponse(responseCode = "404", description = "일정을 찾을 수 없음")
            })
    public ResponseEntity<ScheduleDTO> getScheduleById(
            @Parameter(description = "조회할 일정의 ID") @PathVariable Long scheduleId) {
        ScheduleDTO scheduleDTO = scheduleService.getScheduleDTOById(scheduleId);
        return ResponseEntity.ok(scheduleDTO);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<ScheduleDTO>> getSchedulesByUserId(@PathVariable Long userId) {
//        List<ScheduleDTO> scheduleDTOs = scheduleService.getScheduleDTOsByUserId(userId);
//        return ResponseEntity.ok(scheduleDTOs);
//    }
//
//    @GetMapping("/user/{userId}/range")
//    public ResponseEntity<List<ScheduleDTO>> getSchedulesByDateRange(
//            @PathVariable Long userId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
//        List<ScheduleDTO> scheduleDTOs = scheduleService.getScheduleDTOsByDateRange(userId, startDate, endDate);
//        return ResponseEntity.ok(scheduleDTOs);
//    }
}
