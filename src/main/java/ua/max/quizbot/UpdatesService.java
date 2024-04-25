package ua.max.quizbot;

import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatesService {

    private final Bot bot;

    @Autowired
    public UpdatesService(Bot bot) {
        this.bot = bot;
    }

    public void processUpdate(Update update) {
        Long chatId = update.message().chat().id();
        bot.sendMessage(chatId, update.message().text());
    }
}
