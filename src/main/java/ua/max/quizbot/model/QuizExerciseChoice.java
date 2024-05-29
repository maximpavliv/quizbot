package ua.max.quizbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class QuizExerciseChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizExerciseChoiceId;

    @ManyToOne
    @JoinColumn(name = "quizExerciseId", referencedColumnName = "quizExerciseId")
    private QuizExercise quizExercise;

    @ManyToOne
    @JoinColumn(name = "choiceId", referencedColumnName = "choiceId")
    private Choice choice;

    private Integer idx;
}
