package ua.max.quizbot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @OneToOne
    @JoinColumn(name = "sessionId", referencedColumnName = "sessionId")
    private Session session;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @OrderBy("idx ASC")
    private List<QuizExercise> quizExercises;

    private Integer currentExerciseIdx;

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public List<QuizExercise> getQuizExercises() {
        return quizExercises;
    }

    public void setQuizExercises(List<QuizExercise> quizExercises) {
        this.quizExercises = quizExercises;
    }

    public Integer getCurrentExerciseIdx() {
        return currentExerciseIdx;
    }

    public void setCurrentExerciseIdx(Integer currentExerciseIdx) {
        this.currentExerciseIdx = currentExerciseIdx;
    }
}
