package com.exchangeBE.exchange.service.schedule;

import com.exchangeBE.exchange.dto.RecurrenceCreateDTO;
import com.exchangeBE.exchange.dto.ScheduleCreateDTO;
import com.exchangeBE.exchange.dto.ScheduleDTO;
import com.exchangeBE.exchange.entity.Schedule.*;
import com.exchangeBE.exchange.entity.User.User;
import com.exchangeBE.exchange.repository.Community.UserRepository;
import com.exchangeBE.exchange.repository.schedule.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final TagRepository tagRepository;
    private final RecurrenceRepository recurrenceRepository;
    private final OccasionRepository occasionRepository;
    private final ScheduleTagRepository scheduleTagRepository;

    @Transactional
    public Schedule createOrUpdateSchedule(ScheduleCreateDTO scheduleCreateDto) {
        Schedule schedule;

        // 제공된 유저 ID로 유저 찾음
        User user = userRepository.findById(scheduleCreateDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (scheduleCreateDto.getScheduleId() == null) { // 일정 등록
            schedule = new Schedule();
            schedule.setUser(user);
        } else { // 일정 수정
            schedule = scheduleRepository.findById(scheduleCreateDto.getScheduleId())
                    .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));

            // -- 기존 관계 제거 --
            // 반복 관계 제거
            if (schedule.getRecurrence() != null) {
//                Recurrence recurrence = schedule.getRecurrence();
//                schedule.setRecurrence(null);
//                recurrenceRepository.delete(recurrence);
                recurrenceRepository.delete(schedule.getRecurrence());
                schedule.setRecurrence(null);
            }
            // 태그 관계 제거
            if (schedule.getScheduleTags() != null) {
                schedule.getScheduleTags().clear();
                scheduleTagRepository.deleteBySchedule(schedule);
            }
        }

        // 일정 객체 새로운 값으로 설정
        updateScheduleInfo(schedule, scheduleCreateDto);

        // 일정 객체 저장
        schedule = scheduleRepository.save(schedule);

        // 반복 설정 되어 있는 경우
        if (scheduleCreateDto.getRecurrence() != null) {
            Recurrence recurrence = createRecurrence(scheduleCreateDto.getRecurrence(), schedule);
            schedule.setRecurrence(recurrence);
        }

        // 태그 존재할 경우
        if (scheduleCreateDto.getTagNames() != null && !scheduleCreateDto.getTagNames().isEmpty()) {
            Set<ScheduleTag> scheduleTags = createScheduleTags(scheduleCreateDto.getTagNames(), schedule);
            schedule.setScheduleTags(scheduleTags);
        }

        // 최종 저장
        schedule = scheduleRepository.save(schedule);

        return schedule;
    }

    private void updateScheduleInfo(Schedule schedule, ScheduleCreateDTO scheduleCreateDto) {
        schedule.setScheduleName(scheduleCreateDto.getScheduleName());
        schedule.setScheduleDescription(scheduleCreateDto.getScheduleDescription());
        schedule.setStartTime(scheduleCreateDto.getStartTime());
        schedule.setEndTime(scheduleCreateDto.getEndTime());
    }

    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }


    private Recurrence createRecurrence(RecurrenceCreateDTO recurrenceCreateDto, Schedule schedule) {

        Recurrence recurrence = Recurrence.builder()
                .type(recurrenceCreateDto.getType())
                .daysOfWeek(recurrenceCreateDto.getDaysOfWeek())
                .recurrenceInterval(recurrenceCreateDto.getRecurrenceInterval())
                .build();

        // 반복 정보 저장
        recurrence = recurrenceRepository.save(recurrence);

        // 반복되는 날짜 생성
        Set<Occasion> occasions = generateOccasions(schedule, recurrence, recurrenceCreateDto.getRecurrenceEndDate());
        occasions = new HashSet<>(occasionRepository.saveAll(occasions));  // Occasion들을 저장
        recurrence.setOccasions(occasions);

        return recurrenceRepository.save(recurrence);  // 업데이트된 Recurrence를 다시 저장
    }



    private boolean isOccasionDay(ZonedDateTime date, Recurrence recurrence) {
        switch (recurrence.getType()) {
            case DAILY:
                return true;
            case WEEKLY:
                return recurrence.getDaysOfWeek().contains(date.getDayOfWeek());
            case MONTHLY:
                return date.getDayOfMonth() == recurrence.getRecurrenceInterval();
            case YEARLY:
                // Assuming the first value in daysOfWeek represents the month (1-12)
                int month = recurrence.getDaysOfWeek().iterator().next().getValue();
                return date.getMonthValue() == month &&
                        date.getDayOfMonth() == recurrence.getRecurrenceInterval();
            default:
                return false;
        }
    }

    private Set<Occasion> generateOccasions(Schedule schedule, Recurrence recurrence, ZonedDateTime endDate) {
        Set<Occasion> occasions = new HashSet<>();
        ZonedDateTime recurrenceStart = schedule.getStartTime();
        ZonedDateTime oneMonthLater = recurrenceStart.plusMonths(1);

        // endDate가 한 달 후보다 더 나중이라면 한 달 후로 제한
        ZonedDateTime effectiveEndDate = endDate.isAfter(oneMonthLater) ? oneMonthLater : endDate;

        System.out.println("Generating occasions from " + recurrenceStart + " to " + effectiveEndDate);

        while (recurrenceStart.isBefore(effectiveEndDate) || recurrenceStart.isEqual(effectiveEndDate)) {
            System.out.println("Checking date: " + recurrenceStart.toLocalDate());

            boolean isOccasionDay = isOccasionDay(recurrenceStart, recurrence);
            System.out.println("Is occasion day: " + isOccasionDay);

            if (isOccasionDay) {
                // 일정의 duration 계산
                Duration scheduleDuration = Duration.between(schedule.getStartTime(), schedule.getEndTime());

                // 현재 반복 일정의 종료 시간 계산
                ZonedDateTime occasionEndTime = recurrenceStart.plus(scheduleDuration);

                Occasion occasion = Occasion.builder()
                        .recurrence(recurrence)
                        .startTime(recurrenceStart)
                        .endTime(occasionEndTime)
                        .build();

                boolean added = occasions.add(occasion);
                System.out.println("Occasion added: " + added + ", Occasion: " + occasion);
            }

            // 다음 반복 시작일로 이동
            recurrenceStart = getNextRecurrenceStart(recurrenceStart, recurrence);

            System.out.println("Next recurrence start: " + recurrenceStart);
        }

        System.out.println("Generated " + occasions.size() + " occasions");
        return occasions;
    }

    private ZonedDateTime getNextRecurrenceStart(ZonedDateTime current, Recurrence recurrence) {
        switch (recurrence.getType()) {
            case DAILY:
                return current.plusDays(recurrence.getRecurrenceInterval());
            case WEEKLY:
                return current.plusWeeks(recurrence.getRecurrenceInterval());
            case MONTHLY:
                return current.plusMonths(recurrence.getRecurrenceInterval());
            case YEARLY:
                return current.plusYears(recurrence.getRecurrenceInterval());
            default:
                throw new IllegalArgumentException("Unknown recurrence type");
        }
    }

    private Set<ScheduleTag> createScheduleTags(Set<String> tagNames, Schedule schedule) {
        Set<ScheduleTag> scheduleTags = new HashSet<>();

        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> tagRepository.save(Tag.builder().name(tagName).build()));

            ScheduleTag scheduleTag = ScheduleTag.builder()
                    .tag(tag)
                    .schedule(schedule)
                    .build();

            scheduleTag = scheduleTagRepository.save(scheduleTag);
            scheduleTags.add(scheduleTag);
        }

        return scheduleTags;
    }

    public Schedule getScheduleById(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + scheduleId));
    }

//    public List<Schedule> getSchedulesByUserId(Long userId) {
//        return scheduleRepository.findByUserId(userId);
//    }
//
//    public List<Schedule> getSchedulesByDateRange(Long userId, ZonedDateTime startDate, ZonedDateTime endDate) {
//        return scheduleRepository.findByUserIdAndStartTimeBetween(userId, startDate, endDate);
//    }

    public ScheduleDTO toScheduleDto(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setScheduleName(schedule.getScheduleName());
        dto.setScheduleDescription(schedule.getScheduleDescription());
        dto.setStartTime(schedule.getStartTime());
        dto.setEndTime(schedule.getEndTime());

        dto.setTagNames(schedule.getScheduleTags().stream()
                .map(st -> st.getTag().getName())
                .collect(Collectors.toSet()));

        if (schedule.getRecurrence() != null) {
            ScheduleDTO.RecurrenceDTO recurrenceDTO = new ScheduleDTO.RecurrenceDTO();
            recurrenceDTO.setType(schedule.getRecurrence().getType());
            recurrenceDTO.setDaysOfWeek(schedule.getRecurrence().getDaysOfWeek());
            recurrenceDTO.setRecurrenceInterval(schedule.getRecurrence().getRecurrenceInterval());
            dto.setRecurrence(recurrenceDTO);
        }

        return dto;
    }

    public ScheduleDTO getScheduleDTOById(Long scheduleId) {
        Schedule schedule = getScheduleById(scheduleId);
        return toScheduleDto(schedule);
    }

//    public List<ScheduleDTO> getScheduleDTOsByUserId(Long userId) {
//        List<Schedule> schedules = getSchedulesByUserId(userId);
//        return schedules.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }
//
//    public List<ScheduleDTO> getScheduleDTOsByDateRange(Long userId, ZonedDateTime startDate, ZonedDateTime endDate) {
//        List<Schedule> schedules = getSchedulesByDateRange(userId, startDate, endDate);
//        return schedules.stream().map(this::convertToDTO).collect(Collectors.toList());
//    }
}
