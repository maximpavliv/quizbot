package ua.max.quizbot.controllers;

import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.max.quizbot.services.UpdatesService;

import static com.pengrad.telegrambot.utility.BotUtils.parseUpdate;

@RestController
@RequestMapping("/")
public class UpdatesController {

    private final UpdatesService updatesService;

    @Autowired
    public UpdatesController(UpdatesService updatesService) {
        this.updatesService = updatesService;
    }

    @PostMapping("")
    public ResponseEntity<?> receiveUpdate(@RequestBody String jsonInput) {
        Update update = parseUpdate(jsonInput);
        if (update != null) {
            updatesService.processUpdate(update);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
