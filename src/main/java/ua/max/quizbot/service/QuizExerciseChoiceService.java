package ua.max.quizbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.max.quizbot.model.Choice;
import ua.max.quizbot.model.QuizExercise;
import ua.max.quizbot.model.QuizExerciseChoice;
import ua.max.quizbot.repository.QuizExerciseChoiceRepository;

@Service
public class QuizExerciseChoiceService {

    private final QuizExerciseChoiceRepository quizExerciseChoiceRepository;

    @Autowired
    public QuizExerciseChoiceService(QuizExerciseChoiceRepository quizExerciseChoiceRepository) {
        this.quizExerciseChoiceRepository = quizExerciseChoiceRepository;
    }

    @Transactional
    public QuizExerciseChoice createQuizExerciseChoice(QuizExercise quizExercise, Choice choice, int index) {
        QuizExerciseChoice newQuizExerciseChoice = new QuizExerciseChoice();
        quizExerciseChoiceRepository.save(newQuizExerciseChoice);

        newQuizExerciseChoice.setQuizExercise(quizExercise);
        newQuizExerciseChoice.setChoice(choice);
        newQuizExerciseChoice.setIdx(index);

        return newQuizExerciseChoice;
    }
}
