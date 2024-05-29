package ua.max.quizbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "UserSession")
public class Session {
    public enum State {
        NEW_SESSION,
        DEFINING_QUIZ_LENGTH,
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
}
