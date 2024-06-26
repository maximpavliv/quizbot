package ua.max.quizbot.service;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.max.quizbot.model.Session;
import ua.max.quizbot.record.Exercise;
import ua.max.quizbot.utils.Bot;

@Service
public class UpdatesService {

    private final SessionService sessionService;

    private final Bot bot;

    @Autowired
    public UpdatesService(SessionService sessionService, Bot bot) {
        this.sessionService = sessionService;
        this.bot = bot;
    }

    public void processUpdate(Update update) {
        Long chatId = getChatId(update);
        if (chatId == null)
            return;

        if (!sessionService.sessionExists(chatId))
            sessionService.createSession(chatId);

        Session.State state = sessionService.getSessionState(chatId);
        switch (state) {
            case NEW_SESSION:
                handleUpdateInNewSessionState(chatId, update);
                break;
            case DEFINING_QUIZ_LENGTH:
                handleUpdateInDefiningQuizLengthState(chatId, update);
                break;
            case SOLVING_EXERCISES:
                handleUpdateInSolvingExercisesState(chatId, update);
                break;
            case NEW_TRY:
                handleUpdateInNewTryState(chatId, update);
                break;
        }
    }

    private void handleUpdateInNewSessionState(Long chatId, Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        if (callbackQuery == null) {
            bot.greetUser(chatId);
            askQuizLength(chatId);
            sessionService.setSessionState(chatId, Session.State.DEFINING_QUIZ_LENGTH);
        }
    }

    private void handleUpdateInDefiningQuizLengthState(Long chatId, Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        Integer quizLength = null;
        if (callbackQuery == null) {
            try {
                quizLength = Integer.parseInt(update.message().text());
            } catch (NumberFormatException ignored) {} // quizLength stays null
            if (quizLength == null || quizLength < 1 || quizLength > QuizService.MAX_QUIZ_LENGTH) {
                bot.remindQuizLengthLimits(chatId, 1, QuizService.MAX_QUIZ_LENGTH);
                askQuizLength(chatId);
                return;
            }
        } else {
            if (answeringLastSentMessage(chatId, callbackQuery)) {
                quizLength = Integer.parseInt(callbackQuery.data());
            } else {
                return;
            }
        }
        bot.confirmQuizLength(chatId, quizLength);
        sessionService.loadNewQuiz(chatId, quizLength);
        askQuestion(chatId);
        sessionService.setSessionState(chatId, Session.State.SOLVING_EXERCISES);
    }

    private void handleUpdateInSolvingExercisesState(Long chatId, Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        if (callbackQuery != null) {
            if (answeringLastSentMessage(chatId, callbackQuery)) {
                processAnswer(chatId, Integer.parseInt(callbackQuery.data()));
            } else {
                return;
            }
        } else {
            bot.warnMessageNotUnderstood(chatId);
        }
        if (!sessionService.quizIsFinished(chatId)) {
            askQuestion(chatId);
        } else {
            bot.publishResults(chatId, sessionService.getResults(chatId));
            offerNewTry(chatId);
            sessionService.setSessionState(chatId, Session.State.NEW_TRY);
        }
    }

    private void handleUpdateInNewTryState(Long chatId, Update update) {
        CallbackQuery callbackQuery = update.callbackQuery();
        if (callbackQuery != null)
            if (!answeringLastSentMessage(chatId, callbackQuery))
                return;
        bot.anounceNewTry(chatId);
        askQuizLength(chatId);
        sessionService.setSessionState(chatId, Session.State.DEFINING_QUIZ_LENGTH);
    }

    private Long getChatId(Update update) {
        Long chatId = null;
        if (update.callbackQuery() == null)
        {
            if (update.message() != null && update.message().chat() != null) {
                chatId = update.message().chat().id();
            }
        } else {
            if (update.callbackQuery().maybeInaccessibleMessage() != null
                    && update.callbackQuery().maybeInaccessibleMessage().chat() != null) {
                chatId = update.callbackQuery().maybeInaccessibleMessage().chat().id();
            }
        }
        return chatId;
    }

    private boolean answeringLastSentMessage(Long chatId, CallbackQuery callbackQuery) {
        if (callbackQuery == null)
            return false;
        Integer lastSentMessageId = sessionService.getLastSentMessageId(chatId);
        if (lastSentMessageId != null &&
                lastSentMessageId.equals(callbackQuery.maybeInaccessibleMessage().messageId()))
            return true;
        return false;
    }

    private void askQuizLength(Long chatId) {
        Integer sentMessageId = bot.askQuizLengthAndGetMessageId(chatId);
        sessionService.setLastSentMessageId(chatId, sentMessageId);
    }

    private void askQuestion(Long chatId) {
        Exercise exercise = sessionService.getCurrentExercise(chatId);
        Integer sentMessageId = bot.askQuestionAndGetMessageId(chatId, exercise);
        sessionService.setLastSentMessageId(chatId, sentMessageId);
    }

    private void processAnswer(Long chatId, int userCallbackData) {
        Exercise exercise = sessionService.getCurrentExercise(chatId);
        String userAnswer = exercise.answerChoices().get(userCallbackData);
        bot.repeatUserAnswer(chatId, userAnswer);
        sessionService.saveAnswerAndSwitchToNextExercise(chatId, userCallbackData);
    }

    private void offerNewTry(Long chatId) {
        Integer sentMessageId = bot.offerNewTryAndGetMessageId(chatId);
        sessionService.setLastSentMessageId(chatId, sentMessageId);
    }
}
