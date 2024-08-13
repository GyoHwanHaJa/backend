package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.DynamicResponseBuilder;
import com.exchangeBE.exchange.dto.*;
import com.exchangeBE.exchange.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
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
    public ResponseEntity<?> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        ScheduleDto scheduleDto = scheduleRequestDto.getScheduleDto();
        RecurrenceDto recurrenceDto = scheduleRequestDto.getRecurrenceDto();
        Set<TagDto> tagDto = scheduleRequestDto.getTagDto();

        ScheduleDto updatedScheduleDto = scheduleService.updateSchedule(scheduleId, scheduleDto, recurrenceDto, tagDto);

        return ResponseEntity.ok(updatedScheduleDto);
    }

    /* 일정 삭제 */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }
}
