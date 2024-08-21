package com.exchangeBE.exchange.entity.Report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ReportStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    @OneToMany(mappedBy = "reportStage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportStageOption> selectedOptions = new ArrayList<>();

    @OneToMany(mappedBy = "reportStage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReportStageImage> images = new ArrayList<>();
}