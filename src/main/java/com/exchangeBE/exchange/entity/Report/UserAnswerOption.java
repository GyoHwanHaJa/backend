package com.exchangeBE.exchange.entity.Report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_answer_id")
    private UserAnswer userAnswer;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Options option;
}
