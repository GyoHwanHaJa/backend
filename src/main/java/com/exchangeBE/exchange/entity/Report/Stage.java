package com.exchangeBE.exchange.entity.Report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    private Integer stageNumber;

    private String content;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL)
    private List<Options> options = new ArrayList<>();

    // 사진 최대 4장
    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
}
