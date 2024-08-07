package com.exchangeBE.exchange.controller;

import com.exchangeBE.exchange.dto.RecurrenceDto;
import com.exchangeBE.exchange.dto.ScheduleDto;
import com.exchangeBE.exchange.dto.ScheduleRequestDto;
import com.exchangeBE.exchange.dto.TagDto;
import com.exchangeBE.exchange.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{id}")
    public String getSchedule(@PathVariable Long id) {
        return "Hello World";
    }

    @PostMapping("/{userId}")
    @ResponseBody
    public ScheduleDto createSchedule(@PathVariable Long userId, @RequestBody ScheduleRequestDto scheduleRequestDto) {

        ScheduleDto scheduleDto = scheduleRequestDto.getScheduleDto();
        RecurrenceDto recurrenceDto = scheduleRequestDto.getRecurrenceDto();
        TagDto tagDto = scheduleRequestDto.getTagDto();

        return scheduleService.createSchedule(userId, scheduleDto, recurrenceDto, tagDto);
    }

    @PutMapping("/{id}")
    public String updateSchedule(@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
        scheduleService.updateSchedule(id, scheduleDto);
        return "";
    }

    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return "";
    }
}
