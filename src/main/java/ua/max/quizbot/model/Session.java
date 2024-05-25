package ua.max.quizbot.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserSession")
public class Session {
    public enum State {
        NEW_SESSION,
        SOLVING_EXERCISES,
        NEW_TRY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    private Long chatId;

    @OneToOne(mappedBy = "session", cascade = CascadeType.ALL)
    private Quiz quiz;

    private Integer lastSentMessageId;

    private LocalDateTime lastActivityTime;

    @Enumerated(EnumType.STRING)
    private State state;

    public Session() {}

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Integer getLastSentMessageId() {
        return lastSentMessageId;
    }

    public void setLastSentMessageId(Integer lastSentMessageId) {
        this.lastSentMessageId = lastSentMessageId;
    }

    public LocalDateTime getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(LocalDateTime lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
