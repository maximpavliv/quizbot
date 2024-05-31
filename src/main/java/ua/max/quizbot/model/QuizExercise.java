package ua.max.quizbot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    public Long getQuizExerciseId() {
        return quizExerciseId;
    }

    public void setQuizExerciseId(Long quizExerciseId) {
        this.quizExerciseId = quizExerciseId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<QuizExerciseChoice> getQuizExerciseChoices() {
        return quizExerciseChoices;
    }

    public void setQuizExerciseChoices(List<QuizExerciseChoice> quizExerciseChoices) {
        this.quizExerciseChoices = quizExerciseChoices;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public QuizExerciseChoice getUserQuizExerciseChoice() {
        return userQuizExerciseChoice;
    }

    public void setUserQuizExerciseChoice(QuizExerciseChoice userQuizExerciseChoice) {
        this.userQuizExerciseChoice = userQuizExerciseChoice;
    }
}
