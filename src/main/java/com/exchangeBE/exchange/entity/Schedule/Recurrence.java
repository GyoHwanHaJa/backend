package com.exchangeBE.exchange.entity.Schedule;


import com.exchangeBE.exchange.dto.RecurrenceDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Getter
@Setter
public class Recurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RecurrenceType type;

    @ElementCollection(targetClass= DayOfWeek.class)
    @CollectionTable(name = "recurrence_days", joinColumns = @JoinColumn(name = "recurrence_id"))
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysOfWeek;

    private Integer RecurrenceInterval;

    public static Recurrence toRecurrenceEntity(RecurrenceDto recurrenceDto) {
        Recurrence recurrence = new Recurrence();

        recurrence.setId(recurrenceDto.getId());
        recurrence.setType(recurrenceDto.getType());
        recurrence.setDaysOfWeek(recurrenceDto.getDaysOfWeek());
        recurrence.setRecurrenceInterval(recurrenceDto.getRecurrenceInterval());

        return recurrence;
    }
}
