package ua.max.quizbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.max.quizbot.Quiz;
import ua.max.quizbot.repositories.QuestionRepository;

@Service
public class QuizService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuizService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Quiz createQuiz() {
        return new Quiz(questionRepository.findAll());
    }
}
