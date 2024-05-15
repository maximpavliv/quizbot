package ua.max.quizbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.max.quizbot.model.Quiz;
import ua.max.quizbot.model.Session;
import ua.max.quizbot.record.Exercise;
import ua.max.quizbot.record.QuizResults;
import ua.max.quizbot.repository.SessionRepository;
import ua.max.quizbot.utils.Bot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class SessionService {
    private SessionRepository sessionRepository;

    private final Bot bot;

    private final QuizService quizService;

    // Period for considering a session inactive (1 hour)
    private final static long INACTIVE_THRESHOLD_MS = 3600000;

    @Autowired
    public SessionService(SessionRepository sessionRepository, Bot bot, QuizService quizService) {
        this.sessionRepository = sessionRepository;
        this.bot = bot;
        this.quizService = quizService;
    }

    @Scheduled(fixedRate = INACTIVE_THRESHOLD_MS)
    @Transactional
    public void cleanupInactiveSessions() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Session> sessions = sessionRepository.findAll();
        sessions.stream().filter(session -> {
            if (Duration.between(session.getLastActivityTime(), currentTime).toMillis()
                    > INACTIVE_THRESHOLD_MS) {
                bot.notifySessionHasExpired(session.getChatId());
                return true;
            }
            return false;
        }).forEach(session -> sessionRepository.delete(session));
    }

    @Transactional
    public boolean sessionExists(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId);
        return (session != null);
    }

    @Transactional
    public void createSession(Long chatId) {
        Session newSession = new Session();
        sessionRepository.save(newSession);

        newSession.setQuiz(quizService.createQuiz(newSession));
        newSession.setLastSentMessageId(null);
        newSession.setChatId(chatId);
        newSession.setLastActivityTime(LocalDateTime.now());
    }

    @Transactional
    public boolean quizIsStarted(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId);
        Quiz quiz = quizService.findQuizById(session.getQuiz().getQuizId());
        return quiz.getStarted();
    }

    @Transactional
    public void startQuiz(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId);
        Quiz quiz = quizService.findQuizById(session.getQuiz().getQuizId());
        quiz.setStarted(true);
        updateLastActivityTime(chatId);
    }

    @Transactional
    public Integer getLastSentMessageId(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId);
        return session.getLastSentMessageId();
    }

    @Transactional
    public void setLastSentMessageId(Long chatId, Integer lastSentMessageId) {
        Session session = sessionRepository.findByChatId(chatId);
        session.setLastSentMessageId(lastSentMessageId);
    }

    @Transactional
    public Exercise getCurrentExercise(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId);
        return quizService.getCurrentExercise(session.getQuiz().getQuizId());
    }

    @Transactional
    public void saveAnswerAndSwitchToNextExercise(Long chatId, int userCallbackData) {
        Session session = sessionRepository.findByChatId(chatId);
        quizService.saveAnswerAndSwitchToNextExercise(session.getQuiz().getQuizId(), userCallbackData);
        updateLastActivityTime(chatId);
    }

    @Transactional
    public boolean quizIsFinished(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId);
        return quizService.quizIsFinished(session.getQuiz().getQuizId());
    }

    @Transactional
    public QuizResults getResults(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId);
        return quizService.getResults(session.getQuiz().getQuizId());
    }

    @Transactional
    public void loadNewQuiz(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId);
        Long oldQuizId = session.getQuiz().getQuizId();
        session.setQuiz(null);
        quizService.deleteQuiz(oldQuizId);

        sessionRepository.flush();

        Quiz newQuiz = quizService.createQuiz(session);
        session.setQuiz(newQuiz);
        updateLastActivityTime(chatId);
    }

    @Transactional
    private void updateLastActivityTime(Long chatId) {
        Session session = sessionRepository.findByChatId(chatId);;
        session.setLastActivityTime(LocalDateTime.now());
    }
}
