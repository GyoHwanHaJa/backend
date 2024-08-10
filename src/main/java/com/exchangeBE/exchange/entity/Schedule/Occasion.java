package com.exchangeBE.exchange.entity.Schedule;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Occasion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recurrence_id")
    private Recurrence recurrence; // 여기

    private LocalDateTime time;
}
