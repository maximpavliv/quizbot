package ua.max.quizbot;

import ua.max.quizbot.models.Choice;
import ua.max.quizbot.models.Question;
import ua.max.quizbot.records.Exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Quiz {
    private List<ShuffledExerciseWithSolution> exercisesWithSolutions;

    private List<Integer> userAnswers;

    private boolean started;

    private int currentExerciseIdx;

    public Quiz(List<Question> questions) {

        started = false;
        currentExerciseIdx = 0;
        userAnswers = new ArrayList<>();

        List<ProblemWithSolution> problemsWithSolutions = new ArrayList<>();
        for (Question question : questions) {
            problemsWithSolutions.add(
                    new ProblemWithSolution(question.getQuestionText(),
                            question.getAnswer().getChoice().getChoiceText(),
                            question.getChoiceList().stream()
                                    .filter(choice -> (choice != question.getAnswer().getChoice()))
                                    .map(Choice::getChoiceText)
                                    .toList().toArray(String[]::new)
                    )
            );
        }

        exercisesWithSolutions = problemsWithSolutions.stream()
                .map(ShuffledExerciseWithSolution::new).collect(Collectors.toList());
    }

    public List<ShuffledExerciseWithSolution> getExercisesWithSolutions() {
        return exercisesWithSolutions;
    }

    public void setExercisesWithSolutions(List<ShuffledExerciseWithSolution> exercisesWithSolutions) {
        this.exercisesWithSolutions = exercisesWithSolutions;
    }

    public List<Integer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Integer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public boolean getStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getCurrentExerciseIdx() {
        return currentExerciseIdx;
    }

    public void setCurrentExerciseIdx(int currentExerciseIdx) {
        this.currentExerciseIdx = currentExerciseIdx;
    }

    public record ProblemWithSolution(String question, String rightAnswer, String[] wrongAnswers) {}

    public static class ShuffledExerciseWithSolution {
        private final Exercise exercise;
        private final int solution;

        public ShuffledExerciseWithSolution(ProblemWithSolution problemWithSolution) {
            List<String> answerChoices = new ArrayList<>(Arrays.asList(problemWithSolution.wrongAnswers()));
            answerChoices.add(problemWithSolution.rightAnswer());
            Collections.shuffle(answerChoices);
            solution = answerChoices.indexOf(problemWithSolution.rightAnswer());
            exercise = new Exercise(problemWithSolution.question(), answerChoices);
        }

        public Exercise getExercise() {
            return exercise;
        }

        public int getSolution() {
            return solution;
        }
    }
}
