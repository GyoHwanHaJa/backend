package com.exchangeBE.exchange.entity.Report;

import com.exchangeBE.exchange.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Enumerated(EnumType.STRING)
    private ReportType type; // 생활, 중간, 마감

    // 보고서는 여러 개의 단계로 구성된다.
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Stage> stages = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
