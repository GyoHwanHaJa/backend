package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.RecurrenceDto;
import com.exchangeBE.exchange.dto.ScheduleDto;
import com.exchangeBE.exchange.dto.TagDto;
import com.exchangeBE.exchange.entity.Schedule.Schedule;
import com.exchangeBE.exchange.entity.Schedule.ScheduleTag;
import com.exchangeBE.exchange.entity.Schedule.User;
import com.exchangeBE.exchange.repository.ScheduleRepository;
import com.exchangeBE.exchange.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    public Set<TagDto> createSchedule(Long userId, ScheduleDto scheduleDto,
                                      RecurrenceDto recurrenceDto, Set<TagDto> tagDto) {
        // 받아온 id를 기반으로 유저 검색
        User user = userRepository.findById(userId).get();
        // 특정 유저의 일정으로 설정
        scheduleDto.setUser(user);

        recurrenceDto = recurrenceService.createRecurrence(recurrenceDto);

        // 스케줄에 태그와

        scheduleDto.setRecurrenceDto(recurrenceDto);

        // schedule dto 저장
        scheduleDto = ScheduleDto.toScheduleDto(scheduleRepository.save(Schedule.toScheduleEntity(scheduleDto)));


        // 태그 생성 -> 일정 엔티티와 의존성 X

        for(TagDto tag : tagDto) {
            tag = tagService.createTag(tag); // 태그 레코드 생성
            scheduleTagService.createScheduleTag(scheduleDto, tag); // 일정-태그 조인 테이블 레코드 생성
        }

        return tagDto;
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
