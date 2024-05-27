package ua.max.quizbot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public List<Choice> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Choice> choiceList) {
        this.choiceList = choiceList;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }
}
