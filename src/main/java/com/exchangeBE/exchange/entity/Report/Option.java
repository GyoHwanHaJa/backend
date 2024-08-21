package com.exchangeBE.exchange.entity.Report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "options")
@Getter
@Setter
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    @Column(nullable = false)
    private Integer optionOrder;

    private String content;
}
