package ua.max.quizbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String questionText;

    @OneToMany(mappedBy = "question")
    private List<Choice> choiceList;

    @OneToOne(mappedBy = "question")
    private Answer answer;

    @OneToOne(mappedBy = "question")
    private Snippet snippet;
}
