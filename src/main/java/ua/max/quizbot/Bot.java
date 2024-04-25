package ua.max.quizbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Bot {
    private final TelegramBot telegramBot;

    @Autowired
    public Bot(Environment environment) {
        String botToken = environment.getProperty("QUIZBOT_TELEGRAM_TOKEN");
        if (botToken == null || botToken.isBlank())
            throw new RuntimeException("Bot token is missing");

        // Create Bot
        telegramBot = new TelegramBot(botToken);
    }

    public void sendMessage(@NotNull Long chatId, String text) {
        telegramBot.execute(new SendMessage(chatId, text));
    }
}
