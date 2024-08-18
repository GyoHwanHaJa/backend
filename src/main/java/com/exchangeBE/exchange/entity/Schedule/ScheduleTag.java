package com.exchangeBE.exchange.entity.Schedule;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = {"schedule", "tag"})
@EqualsAndHashCode(of = "id")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"schedule_id", "tag_id"})})
public class ScheduleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    // Additional fields and methods as needed
    public void setSchedule(Schedule schedule) {
        if (this.schedule != null) {
            this.schedule.getScheduleTags().remove(this);
        }
        this.schedule = schedule;
        if (schedule != null) {
            schedule.getScheduleTags().add(this);
        }
    }

    public void setTag(Tag tag) {
        this.tag = tag;
        if (tag != null) {
            tag.getScheduleTags().add(this);
        }
    }
}