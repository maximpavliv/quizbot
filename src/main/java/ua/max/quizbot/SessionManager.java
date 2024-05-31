package ua.max.quizbot;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.max.quizbot.service.QuizService;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionManager {
    private final Bot bot;

    private final QuizService quizService;

    // Period for considering a session inactive (1 hour)
    private final static long INACTIVE_THRESHOLD = 3600000;

    private final Map<Long, Session> sessions;

    @Autowired
    public SessionManager(Bot bot, QuizService quizService) {
        sessions = new HashMap<>();
        this.bot = bot;
        this.quizService = quizService;
    }

    public Session getSessionByChatId(@NotNull Long chatId) {
        if (sessions.containsKey(chatId)) {
            return sessions.get(chatId);
        } else {
            Session session = new Session(chatId);
            sessions.put(chatId, session);
            return session;
        }
    }

    @Scheduled(fixedRate = INACTIVE_THRESHOLD)
    public void cleanupInactiveSessions() {
        long currentTime = System.currentTimeMillis();
        sessions.entrySet()
                .removeIf(entry -> {
                    if ((currentTime - entry.getValue().getLastActivityTime()) > INACTIVE_THRESHOLD) {
                        bot.notifySessionHasExpired(entry.getValue().getChatId());
                        return true;
                    }
                    return false;
                });
    }

    public class Session {
        private Quiz quiz;

        private Long chatId;

        private Integer lastSentMessageId;

        private long lastActivityTime;

        private boolean started;

        public Session(Long chatId) {
            this.quiz = quizService.createQuiz();
            lastSentMessageId = null;
            this.chatId = chatId;
            updateLastActivityTime();
            started = false;
        }

        public boolean hasStarted() {
            return started;
        }

        public void start() {
            started = true;
        }

        public Quiz getQuiz() {
            updateLastActivityTime();
            return quiz;
        }

        public Long getChatId() {
            return chatId;
        }

        public Integer getLastSentMessageId() {
            updateLastActivityTime();
            return lastSentMessageId;
        }

        public void setLastSentMessageId(Integer lastSentMessageId) {
            updateLastActivityTime();
            this.lastSentMessageId = lastSentMessageId;
        }

        public void loadNewQuiz() {
            updateLastActivityTime();
            quiz = quizService.createQuiz();
        }

        public long getLastActivityTime() {
            return lastActivityTime;
        }

        private void updateLastActivityTime() {
            lastActivityTime = System.currentTimeMillis();
        }
    }
}
