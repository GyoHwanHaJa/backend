package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.DynamicResponseBuilder;
import com.exchangeBE.exchange.dto.*;
import com.exchangeBE.exchange.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Tag(name = "Schedule", description = "일정 관련 API")
@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /* 여행 기록 */
    @GetMapping("/history/{userId}")
    @ResponseBody
    public ResponseEntity<?> getMainPage(@PathVariable Long userId,
                                         @RequestBody MainPageRequestDto mainPageRequestDto) {
        Integer year = mainPageRequestDto.getYear();
        Integer month = mainPageRequestDto.getMonth();
        Integer day = mainPageRequestDto.getDay();

        MainPageDto mainPageDto = scheduleService.getMainPage(userId, year, month, day);
        return ResponseEntity.status(HttpStatus.OK).body(mainPageDto);
    }

    /* 일정 단 건 조회 */
    @GetMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<?> getSchedule(@PathVariable Long userId) {
        try {
            ScheduleDto scheduleDto = scheduleService.readSchedule(userId);

            if (scheduleDto == null) {
                return ResponseEntity.notFound().build();
            }

            Map<String, Object> response = DynamicResponseBuilder.buildResponse(
                    "scheduleName", scheduleDto.getScheduleName(),
                    "scheduleDescription", scheduleDto.getScheduleDescription(),
                    "startTime", scheduleDto.getStartTime(),
                    "endTime", scheduleDto.getEndTime()
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러가 발생했습니다: " + e.getMessage());
        }
    }

    /* 일정 조회 */
    // userId 기반 해당 월 일정 조회 (여러 개), 연도, 월
    @GetMapping("/{postId}/{month}")
    public void getNumberOfMonthSchedules(@PathVariable Long postId, @PathVariable Integer month) {

    }
//    @GetMapping("/{userId}")
//    public void getNumberOfMonthSchedules(@PathVariable Long userId) {}

    // 선택한 날짜 기반 일정 조회 (여러 개)
    // scheduleId 기반 일정 조회


    /* 일정 추가 */
    @PostMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<?> createSchedule(@PathVariable Long userId, @RequestBody ScheduleRequestDto scheduleRequestDto) {

        ScheduleDto scheduleDto = scheduleRequestDto.getScheduleDto();
        RecurrenceDto recurrenceDto = scheduleRequestDto.getRecurrenceDto();
        Set<TagDto> tagDto = scheduleRequestDto.getTagDto();


        scheduleService.createSchedule(userId, scheduleDto, recurrenceDto, tagDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 일정 수정 */
    @PutMapping("/{scheduleId}")
    public String updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        //scheduleService.updateSchedule(user);
        return "";
    }

    /* 일정 삭제 */
    @DeleteMapping("/{scheduleId}")
    public String deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return "";
    }
}
