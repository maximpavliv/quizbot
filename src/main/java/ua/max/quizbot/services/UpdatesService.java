package ua.max.quizbot.services;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.max.quizbot.models.Session;
import ua.max.quizbot.records.Exercise;
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

        CallbackQuery callbackQuery = update.callbackQuery();

        if (!sessionService.sessionExists(chatId))
            sessionService.createSession(chatId);

        Session.State state = sessionService.getSessionState(chatId);
        switch (state) {
            case NEW_SESSION:
                if (callbackQuery == null) {
                    bot.greetUser(chatId);
                    askQuestion(chatId);
                    sessionService.setSessionState(chatId, Session.State.SOLVING_EXERCISES);
                }
                break;
            case SOLVING_EXERCISES:
                if (callbackQuery != null) {
                    if (answeringLastSentMessage(chatId, callbackQuery)) {
                        processAnswer(chatId, Integer.parseInt(callbackQuery.data()));
                    } else {
                        break;
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
                break;
            case NEW_TRY:
                if (callbackQuery != null)
                    if (!answeringLastSentMessage(chatId, callbackQuery))
                        break;
                bot.anounceNewTry(chatId);
                sessionService.loadNewQuiz(chatId);
                askQuestion(chatId);
                sessionService.setSessionState(chatId, Session.State.SOLVING_EXERCISES);
                break;
        }
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

    public boolean answeringLastSentMessage(Long chatId, CallbackQuery callbackQuery) {
        if (callbackQuery == null)
            return false;
        Integer lastSentMessageId = sessionService.getLastSentMessageId(chatId);
        if (lastSentMessageId != null &&
                lastSentMessageId.equals(callbackQuery.maybeInaccessibleMessage().messageId()))
            return true;
        return false;
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
        Integer sentMessageId = bot.offerNewTry(chatId);
        sessionService.setLastSentMessageId(chatId, sentMessageId);
    }
}
