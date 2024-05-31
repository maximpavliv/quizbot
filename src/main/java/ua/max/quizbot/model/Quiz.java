package ua.max.quizbot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @OrderBy("idx ASC")
    private List<QuizExercise> quizExercises;

    private Boolean started;

    private Integer currentExerciseIdx;

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public List<QuizExercise> getQuizExercises() {
        return quizExercises;
    }

    public void setQuizExercises(List<QuizExercise> quizExercises) {
        this.quizExercises = quizExercises;
    }

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Integer getCurrentExerciseIdx() {
        return currentExerciseIdx;
    }

    public void setCurrentExerciseIdx(Integer currentExerciseIdx) {
        this.currentExerciseIdx = currentExerciseIdx;
    }
}
