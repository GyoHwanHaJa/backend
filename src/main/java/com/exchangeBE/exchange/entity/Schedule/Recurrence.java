package com.exchangeBE.exchange.entity.Schedule;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = {"occasions", "schedule"})
@EqualsAndHashCode(of = "id")
public class Recurrence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RecurrenceType type;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "recurrence_days", joinColumns = @JoinColumn(name = "recurrence_id"))
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<DayOfWeek> daysOfWeek = new HashSet<>();

    @Min(1)
    private Integer recurrenceInterval;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recurrence_id")
    @Builder.Default
    private Set<Occasion> occasions = new HashSet<>();

    @OneToOne(mappedBy = "recurrence", cascade = CascadeType.ALL, orphanRemoval = true)
    private Schedule schedule;
}