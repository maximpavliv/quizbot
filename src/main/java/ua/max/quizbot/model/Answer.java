package ua.max.quizbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @OneToOne
    @JoinColumn(name = "choiceId", referencedColumnName = "choiceId")
    private Choice choice;

    @OneToOne
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    private Question question;
}
