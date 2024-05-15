package ua.max.quizbot.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserSession")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    private Long chatId;

    @OneToOne(mappedBy = "session", cascade = CascadeType.ALL)
    private Quiz quiz;

    private Integer lastSentMessageId;

    private LocalDateTime lastActivityTime;

    private boolean started;

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

    public boolean getStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
