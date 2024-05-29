package ua.max.quizbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class QuizExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizExerciseId;

    @ManyToOne
    @JoinColumn(name = "quizId", referencedColumnName = "quizId")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    private Question question;

    @OneToMany(mappedBy = "quizExercise", cascade = CascadeType.ALL)
    @OrderBy("idx ASC")
    private List<QuizExerciseChoice> quizExerciseChoices;

    private Integer idx;

    @OneToOne
    @JoinColumn(name = "userQuizExerciseChoiceId", referencedColumnName = "quizExerciseChoiceId")
    private QuizExerciseChoice userQuizExerciseChoice;
}
