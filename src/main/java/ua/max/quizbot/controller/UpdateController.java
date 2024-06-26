package ua.max.quizbot.controller;

import com.pengrad.telegrambot.model.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.max.quizbot.service.UpdatesService;

import static com.pengrad.telegrambot.utility.BotUtils.parseUpdate;

@RestController
@RequestMapping("/")
public class UpdateController {

    private final UpdatesService updatesService;

    @Autowired
    public UpdateController(UpdatesService updatesService) {
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
