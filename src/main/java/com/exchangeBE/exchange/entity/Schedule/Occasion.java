package com.exchangeBE.exchange.entity.Schedule;

import jakarta.persistence.*;
import lombok.*;
import java.time.ZonedDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = {"startTime", "endTime"})  // startTime과 endTime으로 동등성 비교
@ToString(exclude = "recurrence")  // 순환 참조 방지
public class Occasion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recurrence_id")
    private Recurrence recurrence;

    private ZonedDateTime startTime;
    private ZonedDateTime endTime;

    @Enumerated(EnumType.STRING)
    private OccasionStatus status;
}