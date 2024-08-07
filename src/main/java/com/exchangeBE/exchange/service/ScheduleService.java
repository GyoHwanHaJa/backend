package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.RecurrenceDto;
import com.exchangeBE.exchange.dto.ScheduleDto;
import com.exchangeBE.exchange.dto.TagDto;
import com.exchangeBE.exchange.entity.Schedule.Schedule;
import com.exchangeBE.exchange.entity.Schedule.Users;
import com.exchangeBE.exchange.repository.ScheduleRepository;
import com.exchangeBE.exchange.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final RecurrenceService recurrenceService;
    private final ScheduleTagService scheduleTagService;
    private final TagService tagService;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleTagService scheduleTagService,
                           TagService tagService, UserRepository userRepository, RecurrenceService recurrenceService) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleTagService = scheduleTagService;
        this.userRepository = userRepository;
        this.tagService = tagService;
        this.recurrenceService = recurrenceService;
    }

    public ScheduleDto createSchedule(Long userId, ScheduleDto scheduleDto,
                                      RecurrenceDto recurrenceDto, TagDto tagDto) {

        Users user = userRepository.findById(userId).get();

        scheduleDto.setUser(user);


        tagDto = TagDto.toTagDto(tagService.createTag(tagDto));

        recurrenceDto = recurrenceService.createRecurrence(recurrenceDto);
        scheduleDto.setRecurrence_id(recurrenceDto.getId());
        scheduleDto = ScheduleDto.toScheduleDto(scheduleRepository.save(Schedule.toScheduleEntity(scheduleDto)));

        scheduleTagService.createScheduleTag(scheduleDto, tagDto);

        return scheduleDto;
    }

    public void readSchedule(ScheduleDto scheduleDto) {
        Long id = scheduleDto.getId();
        Schedule schedule = scheduleRepository.findById(id).get();
    }

    public void updateSchedule(Long id, ScheduleDto scheduleDto) {
        Schedule schedule = scheduleRepository.findById(id).get();
        schedule = Schedule.toScheduleEntity(scheduleDto);
        scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).get();
        scheduleRepository.delete(schedule);
    }
}
