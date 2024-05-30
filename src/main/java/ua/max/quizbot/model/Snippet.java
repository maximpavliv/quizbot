package ua.max.quizbot.model;

import jakarta.persistence.*;

@Entity
public class Snippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snippetId;

    private String snippetLanguage;

    private String snippetText;

    @OneToOne
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    private Question question;

    public Long getSnippetId() {
        return snippetId;
    }

    public void setSnippetId(Long snippetId) {
        this.snippetId = snippetId;
    }

    public String getSnippetLanguage() {
        return snippetLanguage;
    }

    public void setSnippetLanguage(String snippetLanguage) {
        this.snippetLanguage = snippetLanguage;
    }

    public String getSnippetText() {
        return snippetText;
    }

    public void setSnippetText(String snippetText) {
        this.snippetText = snippetText;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
