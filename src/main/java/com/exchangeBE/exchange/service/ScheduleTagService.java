package com.exchangeBE.exchange.service;

import com.exchangeBE.exchange.dto.ScheduleDto;
import com.exchangeBE.exchange.dto.TagDto;
import com.exchangeBE.exchange.entity.Schedule.Schedule;
import com.exchangeBE.exchange.entity.Schedule.ScheduleTag;
import com.exchangeBE.exchange.entity.Schedule.Tag;
import com.exchangeBE.exchange.repository.ScheduleTagRepository;
import org.springframework.stereotype.Service;

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

        scheduleTag.setTag(Tag.toTagEntity(tagDto));
        scheduleTag.setSchedule(Schedule.toScheduleEntity(scheduleDto));

        

        scheduleTag = scheduleTagRepository.save(scheduleTag);

        System.out.println("여기부터");
        System.out.println("tag" + scheduleTag.getTag());
        System.out.println("schedule" + scheduleTag.getSchedule());
        
        
        return scheduleTag;
    }
}
