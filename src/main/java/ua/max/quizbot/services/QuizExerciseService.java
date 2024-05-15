package ua.max.quizbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.max.quizbot.models.*;
import ua.max.quizbot.records.Exercise;
import ua.max.quizbot.repositories.QuizExerciseRepository;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class QuizExerciseService {

    private final QuizExerciseRepository quizExerciseRepository;

    private final QuizExerciseChoiceService quizExerciseChoiceService;

    @Autowired
    public QuizExerciseService(QuizExerciseRepository quizExerciseRepository,
                               QuizExerciseChoiceService quizExerciseChoiceService) {
        this.quizExerciseRepository = quizExerciseRepository;
        this.quizExerciseChoiceService = quizExerciseChoiceService;
    }

    @Transactional
    public QuizExercise createQuizExercise(Quiz quiz, Question question, int index) {
        QuizExercise newQuizExercise = new QuizExercise();
        quizExerciseRepository.save(newQuizExercise);

        newQuizExercise.setQuiz(quiz);
        newQuizExercise.setQuestion(question);

        List<Choice> choices = question.getChoiceList();
        Collections.shuffle(choices);
        List<QuizExerciseChoice> quizExerciseChoices = choices.stream()
                .map(choice ->
                        quizExerciseChoiceService.createQuizExerciseChoice(
                                newQuizExercise, choice, choices.indexOf(choice)))
                .toList();
        newQuizExercise.setQuizExerciseChoices(quizExerciseChoices);

        newQuizExercise.setIdx(index);
        newQuizExercise.setUserQuizExerciseChoice(null);

        return newQuizExercise;
    }

    @Transactional
    public Exercise getExercise(Long quizExerciseId) {
        QuizExercise quizExercise = quizExerciseRepository.getReferenceById(quizExerciseId);
        return new Exercise(quizExercise.getQuestion().getQuestionText(),
                quizExercise.getQuizExerciseChoices().stream()
                        .map(QuizExerciseChoice::getChoice).map(Choice::getChoiceText)
                        .collect(Collectors.toList()));
    }

    @Transactional
    public QuizExercise findQuizExerciseById(Long quizExerciseId) {
        return quizExerciseRepository.getReferenceById(quizExerciseId);
    }

    @Transactional
    public void saveUserAnswer(Long quizExerciseId, int userQuizExerciseChoiceIdx) {
        QuizExercise quizExercise = quizExerciseRepository.getReferenceById(quizExerciseId);
        quizExercise.getQuizExerciseChoices().stream()
                .filter(quizExerciseChoice -> quizExerciseChoice.getIdx().equals(userQuizExerciseChoiceIdx))
                .findAny().ifPresentOrElse(
                        quizExercise::setUserQuizExerciseChoice,
                        () -> {throw new NoSuchElementException("No choice found for the given index");}
                );
    }

    @Transactional
    public Choice getUserChoice(Long quizExerciseId) {
        QuizExercise quizExercise = quizExerciseRepository.getReferenceById(quizExerciseId);
        return quizExercise.getUserQuizExerciseChoice().getChoice();
    }

    @Transactional
    public Choice getCorrectChoice(Long quizExerciseId) {
        QuizExercise quizExercise = quizExerciseRepository.getReferenceById(quizExerciseId);
        return quizExercise.getQuestion().getAnswer().getChoice();
    }
}
