package ua.max.quizbot;

import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.max.quizbot.record.Exercise;

@Service
public class UpdatesService {

    private Quiz quiz;

    private Integer lastSentMessageId;

    private final Bot bot;

    @Autowired
    public UpdatesService(Bot bot) {
        this.quiz = new Quiz();
        this.lastSentMessageId = null;
        this.bot = bot;
    }

    public void processUpdate(Update update) {
        Long chatId = getChatId(update);
        if (chatId == null)
            return;

        CallbackQuery callbackQuery = update.callbackQuery();

        if (!quiz.isStarted()) {
            // ensure that this isn't triggered by a press on a keyboard
            if (callbackQuery == null) {
                bot.greetUser(chatId);
                quiz.start();
            } else return;
        } else {
            // ensure user is answering to keyboard
            if (callbackQuery != null) {
                // ensure user is answering to last question
                if (lastSentMessageId != null &&
                        lastSentMessageId.equals(callbackQuery.maybeInaccessibleMessage().messageId())) {
                    int userCallbackData = Integer.parseInt(callbackQuery.data());
                    Exercise exercise = quiz.getExercise();
                    String userAnswer = exercise.answerChoices().get(userCallbackData);
                    bot.repeatUserAnswer(chatId, userAnswer);
                    quiz.saveAnswerAndSwitchToNextExercise(userCallbackData);
                } else return;
            } else {
                // Say user's input wasn't understood
                bot.warnMessageNotUnderstood(chatId);
            }
        }

        // ask question if quiz not finished, otherwise print results and reset session
        if (!quiz.isFinished()) {
            askQuestion(chatId);
        } else {
            bot.publishResults(chatId, quiz.getResults());
            quiz = new Quiz();
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
        Exercise exercise = quiz.getExercise();
        Integer sentMessageId = bot.askQuestionAndGetMessageId(chatId, exercise);
        lastSentMessageId = sentMessageId;
    }
}
