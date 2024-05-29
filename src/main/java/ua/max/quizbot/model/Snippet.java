package ua.max.quizbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Snippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snippetId;

    private String snippetLanguage;

    private String snippetText;

    @OneToOne
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    private Question question;
}
