package ua.max.quizbot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @PostMapping("")
    public ResponseEntity<?> sayHello() {
        System.out.println("Hello!");
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
