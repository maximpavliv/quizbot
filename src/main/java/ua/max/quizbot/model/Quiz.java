package ua.max.quizbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
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
}
