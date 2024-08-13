package com.exchangeBE.exchange.service.schedule;

import com.exchangeBE.exchange.dto.RecurrenceDto;
import com.exchangeBE.exchange.entity.Schedule.Recurrence;
import com.exchangeBE.exchange.repository.schedule.RecurrenceRepository;
import org.springframework.stereotype.Service;

@Service
public class RecurrenceService {
    private final RecurrenceRepository recurrenceRepository;
    private final OccasionService occasionService;

    public RecurrenceService(RecurrenceRepository recurrenceRepository, OccasionService occasionService) {
        this.recurrenceRepository = recurrenceRepository;
        this.occasionService = occasionService;
    }

    public RecurrenceDto createRecurrence(RecurrenceDto recurrenceDto) {
        Recurrence recurrence = Recurrence.toRecurrenceEntity(recurrenceDto);
        recurrence = recurrenceRepository.save(recurrence);

        // 일정 생성할 때, 반복 여부를 파악.
        // 이에 따라 반복일 적용 -> occasion 날짜 계산하여 저장
//        occasionService.createOccasion();



        return RecurrenceDto.toRecurrenceDto(recurrence);
    }

    public RecurrenceDto updateRecurrence(RecurrenceDto recurrenceDto) {
        return RecurrenceDto.toRecurrenceDto(recurrenceRepository.save(Recurrence.toRecurrenceEntity(recurrenceDto)));
    }
    private void dailyRecurrence() {

    }
    private void weeklyRecurrence() {

    }
}
