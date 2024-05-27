package ua.max.quizbot.utils;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import ua.max.quizbot.record.Exercise;
import ua.max.quizbot.record.QuizResults;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.SequencedMap;

@Component
public class Bot {
    private final TelegramBot telegramBot;

    private final ResourceBundle bundle;

    @Autowired
    public Bot(Environment environment) {
        String botToken = environment.getProperty("QUIZBOT_TELEGRAM_TOKEN");
        if (botToken == null || botToken.isBlank())
            throw new RuntimeException("Bot token is missing");

        // Create Bot
        telegramBot = new TelegramBot(botToken);

        bundle = ResourceBundle.getBundle("messages");
    }

    public void greetUser(@NotNull Long chatId) {
        sendMessage(chatId, bundle.getString("quiz_starting_message"));
    }

    public Integer askQuizLengthAndGetMessageId(Long chatId) {
        SendMessage message = new SendMessage(chatId, bundle.getString("ask_quiz_length"));
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup()
                .addRow(new InlineKeyboardButton("3").callbackData("3"))
                .addRow(new InlineKeyboardButton("5").callbackData("5"))
                .addRow(new InlineKeyboardButton("10").callbackData("10"));

        SendResponse response = telegramBot.execute(message.replyMarkup(keyboard));

        Integer messageId = null;
        if (response != null && response.message() != null)
            messageId = response.message().messageId();
        return messageId;
    }

    public void remindQuizLengthLimits(Long chatId, int minQuizLength, int maxQuizLength) {
        String message = MessageFormat.format(bundle.getString("remind_quiz_length_limits"),
                minQuizLength, maxQuizLength);
        sendMessage(chatId, message);
    }

    public void confirmQuizLength(Long chatId, int quizLength) {
        String message = MessageFormat.format(bundle.getString("confirm_quiz_length"), quizLength);
        sendMessage(chatId, message);
    }

    public Integer askQuestionAndGetMessageId(@NotNull Long chatId, Exercise exercise) {
        String questionText = MessageFormat.format(bundle.getString("ask_question_format"),
                exercise.questionIndex().toString(), exercise.question());
        if (exercise.snippet() != null) {
            questionText += MessageFormat.format(bundle.getString("add_snippet_format"),
                    exercise.snippet().language(), exercise.snippet().snippet());
        }
        SendMessage message = new SendMessage(chatId, questionText).parseMode(ParseMode.Markdown);

        List<String> answerChoices = exercise.answerChoices();
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        for (int i=0; i<answerChoices.size(); i++) {
            keyboard.addRow(new InlineKeyboardButton(answerChoices.get(i)).callbackData(String.valueOf(i)));
        }

        SendResponse response = telegramBot.execute(message.replyMarkup(keyboard));

        Integer messageId = null;
        if (response != null && response.message() != null)
            messageId = response.message().messageId();
        return messageId;
    }

    public void repeatUserAnswer(@NotNull Long chatId, String userAnswer) {
        sendMessage(chatId, bundle.getString("users_answer_is") + userAnswer);
    }

    public void warnMessageNotUnderstood(@NotNull Long chatId) {
        sendMessage(chatId, bundle.getString("reply_not_understood_message"));
    }

    public void publishResults(@NotNull Long chatId, @NotNull QuizResults results) {
        sendMessage(chatId, bundle.getString("quiz_finished_message") + results.score());
        SequencedMap<Integer, String> corrections = results.corrections();
        for (Integer exerciseIdx : corrections.sequencedKeySet()) {
            sendMessage(chatId, MessageFormat.format(
                    bundle.getString("correct_answer_to_question_was"), exerciseIdx, corrections.get(exerciseIdx)));
        }
    }

    public Integer offerNewTryAndGetMessageId(Long chatId) {
        SendMessage message = new SendMessage(chatId, bundle.getString("offer_new_try"));
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup()
                .addRow(new InlineKeyboardButton("OK").callbackData("OK"));

        SendResponse response = telegramBot.execute(message.replyMarkup(keyboard));

        Integer messageId = null;
        if (response != null && response.message() != null)
            messageId = response.message().messageId();
        return messageId;
    }

    public void anounceNewTry(@NotNull Long chatId) {
        sendMessage(chatId, bundle.getString("quiz_new_try_message"));
    }

    public void notifySessionHasExpired(@NotNull Long chatId) {
        sendMessage(chatId, bundle.getString("session_expired"));
    }

    private void sendMessage(@NotNull Long chatId, String text) {
        telegramBot.execute(new SendMessage(chatId, text));
    }
}
