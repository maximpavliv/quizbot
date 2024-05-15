package ua.max.quizbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.max.quizbot.Quiz;
import ua.max.quizbot.records.Exercise;
import ua.max.quizbot.records.QuizResults;
import ua.max.quizbot.repositories.QuestionRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.SequencedMap;
import java.util.stream.IntStream;

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

    public Exercise getCurrentExercise(Quiz quiz) {
        List<Quiz.ShuffledExerciseWithSolution> exercisesWithSolutions = quiz.getExercisesWithSolutions();
        int currentExerciseIdx = quiz.getCurrentExerciseIdx();
        return exercisesWithSolutions.get(currentExerciseIdx).getExercise();
    }

    public void saveAnswerAndSwitchToNextExercise(Quiz quiz, int userAnswer) {
        List<Integer> userAnswers = quiz.getUserAnswers();
        userAnswers.add(userAnswer);
        quiz.setCurrentExerciseIdx(quiz.getCurrentExerciseIdx() + 1);
    }

    public boolean quizIsFinished(Quiz quiz) {
        return (quiz.getCurrentExerciseIdx() >= quiz.getExercisesWithSolutions().size());
    }

    public QuizResults getResults(Quiz quiz) {
        if (!quizIsFinished(quiz))
            return null;

        List<Quiz.ShuffledExerciseWithSolution> exercisesWithSolutions = quiz.getExercisesWithSolutions();
        List<Integer> userAnswers = quiz.getUserAnswers();

        int numberQuestions = exercisesWithSolutions.size();
        long correctAnswersCount = IntStream.range(0, numberQuestions)
                .filter(i -> userAnswers.get(i).equals(exercisesWithSolutions.get(i).getSolution()))
                .count();
        String score = correctAnswersCount + "/" + numberQuestions;

        SequencedMap<String, String> corrections = new LinkedHashMap<>();
        for (int i=0; i<exercisesWithSolutions.size(); i++) {
            Quiz.ShuffledExerciseWithSolution exerciseWithSolution = exercisesWithSolutions.get(i);
            int userAnswer = userAnswers.get(i);
            if (exerciseWithSolution.getSolution() != userAnswer) {
                corrections.put(
                        exerciseWithSolution.getExercise().question(),
                        exerciseWithSolution.getExercise().answerChoices().get(exerciseWithSolution.getSolution()));
            }
        }
        return new QuizResults(score, corrections);
    }
}
