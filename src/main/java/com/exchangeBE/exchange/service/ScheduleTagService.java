package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.ScheduleDto;
import com.exchangeBE.exchange.dto.TagDto;
import com.exchangeBE.exchange.entity.Schedule.Schedule;
import com.exchangeBE.exchange.entity.Schedule.ScheduleTag;
import com.exchangeBE.exchange.entity.Schedule.Tag;
import com.exchangeBE.exchange.repository.ScheduleTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class ScheduleTagService {
    // find by name -> tagRepo
    ScheduleTagRepository scheduleTagRepository;

    public ScheduleTagService(ScheduleTagRepository scheduleTagRepository) {
        this.scheduleTagRepository = scheduleTagRepository;
    }

    public ScheduleTag createScheduleTag(ScheduleDto scheduleDto, TagDto tagDto) {
        ScheduleTag scheduleTag = new ScheduleTag();

        scheduleTag.setTag(Tag.toTagEntity(tagDto)); // 얘가 핵심이네
        scheduleTag.setSchedule(Schedule.toScheduleEntity(scheduleDto));

        scheduleTag = scheduleTagRepository.save(scheduleTag);
        
        return scheduleTag;
    }

    public ScheduleTag updateScheduleTag(ScheduleDto scheduleDto, TagDto tagDto) {
        ScheduleTag oldScheduleTag = scheduleTagRepository.findBySchedule(Schedule.toScheduleEntity(scheduleDto));

        oldScheduleTag.setSchedule(Schedule.toScheduleEntity(scheduleDto));
        oldScheduleTag.setTag(Tag.toTagEntity(tagDto));

        return scheduleTagRepository.save(oldScheduleTag);
    }
}
