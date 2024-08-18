package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.ScheduleCreateDTO;
import com.exchangeBE.exchange.dto.ScheduleDTO;
import com.exchangeBE.exchange.entity.Schedule.Schedule;

import com.exchangeBE.exchange.service.schedule.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public Schedule createSchedule(@RequestBody ScheduleCreateDTO dto) {
        scheduleService.createOrUpdateSchedule(dto);
        return null;
    }

    @PutMapping("/{scheduleId}")
    public Schedule updateSchedule(@RequestBody ScheduleCreateDTO dto, @PathVariable Long scheduleId) {
        dto.setScheduleId(scheduleId);
        scheduleService.createOrUpdateSchedule(dto);
        return null;
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {
        ScheduleDTO scheduleDTO = scheduleService.getScheduleDTOById(id);
        return ResponseEntity.ok(scheduleDTO);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByUserId(@PathVariable Long userId) {
        List<ScheduleDTO> scheduleDTOs = scheduleService.getScheduleDTOsByUserId(userId);
        return ResponseEntity.ok(scheduleDTOs);
    }

    @GetMapping("/user/{userId}/range")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesByDateRange(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate) {
        List<ScheduleDTO> scheduleDTOs = scheduleService.getScheduleDTOsByDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(scheduleDTOs);
    }
}
