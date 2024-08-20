package com.exchangeBE.exchange.entity.Report;

import com.exchangeBE.exchange.entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToMany(mappedBy = "userAnswer", cascade = CascadeType.ALL)
    private List<UserAnswerOption> options = new ArrayList<>();
}
