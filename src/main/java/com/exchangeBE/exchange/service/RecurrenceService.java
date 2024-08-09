package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.RecurrenceDto;
import com.exchangeBE.exchange.entity.Schedule.Recurrence;
import com.exchangeBE.exchange.repository.RecurrenceRepository;
import org.springframework.stereotype.Service;

@Service
public class RecurrenceService {
    private final RecurrenceRepository recurrenceRepository;

    public RecurrenceService(RecurrenceRepository recurrenceRepository) {
        this.recurrenceRepository = recurrenceRepository;
    }

    public RecurrenceDto createRecurrence(RecurrenceDto recurrenceDto) {
        Recurrence recurrence = Recurrence.toRecurrenceEntity(recurrenceDto);
        recurrence = recurrenceRepository.save(recurrence);

        return RecurrenceDto.toRecurrenceDto(recurrence);
    }
}
