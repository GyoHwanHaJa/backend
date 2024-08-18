package com.exchangeBE.exchange.entity.Schedule;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = "scheduleTags")
@EqualsAndHashCode(of = "id")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ScheduleTag> scheduleTags = new HashSet<>();

    // Helper methods for bidirectional relationship management
    public void addScheduleTag(ScheduleTag scheduleTag) {
        scheduleTags.add(scheduleTag);
        scheduleTag.setTag(this);
    }

    public void removeScheduleTag(ScheduleTag scheduleTag) {
        scheduleTags.remove(scheduleTag);
        scheduleTag.setTag(null);
    }
}