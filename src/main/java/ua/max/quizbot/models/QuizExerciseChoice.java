package ua.max.quizbot.models;

import jakarta.persistence.*;

@Entity
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

    public Long getQuizExerciseChoiceId() {
        return quizExerciseChoiceId;
    }

    public void setQuizExerciseChoiceId(Long quizExerciseChoiceId) {
        this.quizExerciseChoiceId = quizExerciseChoiceId;
    }

    public QuizExercise getQuizExercise() {
        return quizExercise;
    }

    public void setQuizExercise(QuizExercise quizExercise) {
        this.quizExercise = quizExercise;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }
}
