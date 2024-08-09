package com.exchangeBE.exchange.entity.Schedule;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Occassion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recurrence_id", nullable = false)
    private Recurrence recurrence; // 여기

    private LocalDateTime time;
}
