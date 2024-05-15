package ua.max.quizbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.max.quizbot.records.Exercise;
import ua.max.quizbot.records.QuizResults;
import ua.max.quizbot.services.QuizService;
import ua.max.quizbot.utils.Bot;

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

    public boolean sessionExists(Long chatId) {
        return sessions.containsKey(chatId);
    }

    public void createSession(Long chatId) {
        Session newSession = new Session();

        newSession.setQuiz(quizService.createQuiz());
        newSession.setLastSentMessageId(null);
        newSession.setChatId(chatId);
        newSession.setLastActivityTime(System.currentTimeMillis());
        newSession.setStarted(false);

        sessions.put(chatId, newSession);
    }

    public boolean sessionHasStarted(Long chatId) {
        Session session = sessions.get(chatId);
        return session.getStarted();
    }

    public void startSession(Long chatId) {
        Session session = sessions.get(chatId);
        session.setStarted(true);
        updateLastActivityTime(chatId);
    }

    public boolean quizIsStarted(Long chatId) {
        Session session = sessions.get(chatId);
        Quiz quiz = session.getQuiz();
        return quiz.getStarted();
    }

    public void startQuiz(Long chatId) {
        Session session = sessions.get(chatId);
        Quiz quiz = session.getQuiz();
        quiz.setStarted(true);
        updateLastActivityTime(chatId);
    }

    public Integer getLastSentMessageId(Long chatId) {
        Session session = sessions.get(chatId);
        return session.getLastSentMessageId();
    }

    public void setLastSentMessageId(Long chatId, Integer lastSentMessageId) {
        Session session = sessions.get(chatId);
        session.setLastSentMessageId(lastSentMessageId);
    }

    public Exercise getCurrentExercise(Long chatId) {
        Session session = sessions.get(chatId);
       return quizService.getCurrentExercise(session.getQuiz());
    }

    public void saveAnswerAndSwitchToNextExercise(Long chatId, int userAnswer) {
        Session session = sessions.get(chatId);
        quizService.saveAnswerAndSwitchToNextExercise(session.getQuiz(), userAnswer);
        updateLastActivityTime(chatId);
    }

    public boolean quizIsFinished(Long chatId) {
        Session session = sessions.get(chatId);
        return quizService.quizIsFinished(session.getQuiz());
    }

    public QuizResults getResults(Long chatId) {
        Session session = sessions.get(chatId);
        return quizService.getResults(session.getQuiz());
    }

    public void loadNewQuiz(Long chatId) {
        Session session = sessions.get(chatId);
        session.setQuiz(quizService.createQuiz());
        updateLastActivityTime(chatId);
    }

    private void updateLastActivityTime(Long chatId) {
        Session session = sessions.get(chatId);
        session.setLastActivityTime(System.currentTimeMillis());
    }

    public class Session {
        private Long chatId;

        private Quiz quiz;

        private Integer lastSentMessageId;

        private long lastActivityTime;

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

        public long getLastActivityTime() {
            return lastActivityTime;
        }

        private void setLastActivityTime(long lastActivityTime) {
            this.lastActivityTime = lastActivityTime;
        }

        public boolean getStarted() {
            return started;
        }

        public void setStarted(boolean started) {
            this.started = started;
        }
    }
}
