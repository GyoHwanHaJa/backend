package com.exchangeBE.exchange.service.schedule;

import com.exchangeBE.exchange.dto.MainPageDto;
import com.exchangeBE.exchange.dto.RecurrenceDto;
import com.exchangeBE.exchange.dto.ScheduleDto;
import com.exchangeBE.exchange.dto.TagDto;
import com.exchangeBE.exchange.entity.Schedule.Schedule;
import com.exchangeBE.exchange.entity.User;
import com.exchangeBE.exchange.repository.schedule.ScheduleRepository;
import com.exchangeBE.exchange.repository.schedule.ScheduleTagRepository;
import com.exchangeBE.exchange.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final RecurrenceService recurrenceService;
    private final ScheduleTagService scheduleTagService;
    private final TagService tagService;
    private final UserRepository userRepository;
    private final ScheduleTagRepository scheduleTagRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleTagService scheduleTagService,
                           TagService tagService, UserRepository userRepository, RecurrenceService recurrenceService, ScheduleTagRepository scheduleTagRepository) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleTagService = scheduleTagService;
        this.userRepository = userRepository;
        this.tagService = tagService;
        this.recurrenceService = recurrenceService;
        this.scheduleTagRepository = scheduleTagRepository;
    }

    public Set<TagDto> createSchedule(Long userId, ScheduleDto scheduleDto,
                                      RecurrenceDto recurrenceDto, Set<TagDto> tagDto) {
        // 받아온 id를 기반으로 유저 검색
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        // 일정 추가 요청한 유저 설정
        scheduleDto.setUser(user);

        // recurrenceId 받아옴
        recurrenceDto = recurrenceService.createRecurrence(recurrenceDto);
        // 일정의 반복 등록
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

    public ScheduleDto readSchedule(Long userId) {
        Schedule schedule = scheduleRepository.findById(userId).get();
        return ScheduleDto.toScheduleDto(schedule);
    }

    public ScheduleDto updateSchedule(Long scheduleId, ScheduleDto scheduleDto, RecurrenceDto recurrenceDto, Set<TagDto> tagDto) {

        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        //ScheduleDto updateScheduleDto = ScheduleDto.toScheduleDto(schedule);
        scheduleDto.setId(scheduleId);
        recurrenceDto.setId(schedule.getRecurrence().getId());
        recurrenceDto = recurrenceService.updateRecurrence(recurrenceDto);

        scheduleDto.setRecurrenceDto(recurrenceDto);
        scheduleDto.setId(schedule.getId());
        scheduleDto.setUser(schedule.getUser());
        scheduleDto.setScheduleTags(schedule.getScheduleTags());

        scheduleDto = ScheduleDto.toScheduleDto(scheduleRepository.save(Schedule.toScheduleEntity(scheduleDto)));

        for(TagDto tag : tagDto) {
            tag = tagService.createTag(tag);
            scheduleTagService.createScheduleTag(scheduleDto, tag);
        }

        return scheduleDto;
        //return ScheduleDto.toScheduleDto(scheduleRepository.save(schedule));
    }

    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        scheduleRepository.delete(schedule);
    }

    public MainPageDto getMainPage(Long userId, Integer year, Integer month, Integer day) {
        User user = userRepository.findById(userId).get();
        LocalDateTime now = LocalDateTime.now();
        Long dDay = ChronoUnit.DAYS.between(now, user.getExchangePeriodEnd()); // 귀국 남은 날짜

        // 보고서 개수
        Integer count = scheduleRepository.countByUserId(userId);


        // 해당 월의 일정 날짜
        List<Schedule> scheduleList = scheduleRepository.findAllByYearAndMonth(year, month);
        List<Integer> dayList = new ArrayList<>();

        for (Schedule schedule : scheduleList) {
            dayList.add(schedule.getStartTime().getDayOfMonth());
        }

        // 오늘 일정
        LocalDate date = LocalDate.of(year, month, day);
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List <Schedule> todaySchedule = scheduleRepository.findAllByStartTimeBetween(startOfDay, endOfDay);
        List<String> scheduleNameList = new ArrayList<>();
        List<LocalDateTime> scheduleTimeList = new ArrayList<>();

        for (Schedule schedule : todaySchedule) {
            scheduleNameList.add(schedule.getScheduleName());
            scheduleTimeList.add(schedule.getStartTime());
        }

        MainPageDto mainPageDto = new MainPageDto();
        mainPageDto.setScheduleNameList(scheduleNameList);
        mainPageDto.setScheduleTimeList(scheduleTimeList);
        mainPageDto.setDayList(dayList);
        mainPageDto.setDDay(dDay);
        mainPageDto.setCount(count);

        return mainPageDto;
    }
}
