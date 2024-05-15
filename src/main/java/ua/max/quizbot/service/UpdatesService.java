package ua.max.quizbot.service;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.max.quizbot.SessionManager;
import ua.max.quizbot.record.Exercise;
import ua.max.quizbot.utils.Bot;

@Service
public class UpdatesService {

    private final SessionManager sessionManager;

    private final Bot bot;

    @Autowired
    public UpdatesService(SessionManager sessionManager, Bot bot) {
        this.sessionManager = sessionManager;
        this.bot = bot;
    }

    public void processUpdate(Update update) {
        Long chatId = getChatId(update);
        if (chatId == null)
            return;

        CallbackQuery callbackQuery = update.callbackQuery();

        if (!sessionManager.sessionExists(chatId))
            sessionManager.createSession(chatId);

        if (!sessionManager.quizIsStarted(chatId)) {
            // ensure that this isn't triggered by a press on a keyboard
            if (callbackQuery == null) {
                if (!sessionManager.sessionHasStarted(chatId)) {
                    bot.greetUser(chatId);
                    sessionManager.startSession(chatId);
                } else {
                    bot.anounceNewTry(chatId);
                }
                sessionManager.startQuiz(chatId);
            } else return;
        } else {
            // ensure user is answering to keyboard
            if (callbackQuery != null) {
                // ensure user is answering to last question
                Integer lastSentMessageId = sessionManager.getLastSentMessageId(chatId);
                if (lastSentMessageId != null &&
                        lastSentMessageId.equals(callbackQuery.maybeInaccessibleMessage().messageId())) {
                    int userCallbackData = Integer.parseInt(callbackQuery.data());
                    Exercise exercise = sessionManager.getCurrentExercise(chatId);
                    String userAnswer = exercise.answerChoices().get(userCallbackData);
                    bot.repeatUserAnswer(chatId, userAnswer);
                    sessionManager.saveAnswerAndSwitchToNextExercise(chatId, userCallbackData);
                } else return;
            } else {
                // Say user's input wasn't understood
                bot.warnMessageNotUnderstood(chatId);
            }
        }

        // ask question if quiz not finished, otherwise print results and reset session
        if (!sessionManager.quizIsFinished(chatId)) {
            askQuestion(chatId);
        } else {
            bot.publishResults(chatId, sessionManager.getResults(chatId));
            sessionManager.loadNewQuiz(chatId);
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

    private void askQuestion(Long chatId) {
        Exercise exercise = sessionManager.getCurrentExercise(chatId);
        Integer sentMessageId = bot.askQuestionAndGetMessageId(chatId, exercise);
        sessionManager.setLastSentMessageId(chatId, sentMessageId);
    }
}
