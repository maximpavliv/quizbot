package ua.max.quizbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.max.quizbot.models.Choice;
import ua.max.quizbot.models.QuizExercise;
import ua.max.quizbot.models.QuizExerciseChoice;
import ua.max.quizbot.repositories.QuizExerciseChoiceRepository;

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
