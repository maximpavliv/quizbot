package ua.max.quizbot;

import ua.max.quizbot.model.Choice;
import ua.max.quizbot.model.Question;
import ua.max.quizbot.record.Exercise;
import ua.max.quizbot.record.QuizResults;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Quiz {
    private final List<ShuffledExerciseWithSolution> exercisesWithSolutions;

    private final List<Integer> userAnswers;

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

    public boolean isStarted() {
        return started;
    }

    public void start() {
        started = true;
    }

    public boolean isFinished() {
        return (currentExerciseIdx >= exercisesWithSolutions.size());
    }

    public Exercise getExercise() {
        return exercisesWithSolutions.get(currentExerciseIdx).getExercise();
    }

    public void saveAnswerAndSwitchToNextExercise(Integer userAnswer) {
        userAnswers.add(userAnswer);
        currentExerciseIdx++;
    }

    public QuizResults getResults() {
        if (!isFinished())
            return null;

        int numberQuestions = exercisesWithSolutions.size();
        long correctAnswersCount = IntStream.range(0, numberQuestions)
                .filter(i -> userAnswers.get(i).equals(exercisesWithSolutions.get(i).getSolution()))
                .count();
        String score = correctAnswersCount + "/" + numberQuestions;

        SequencedMap<String, String> corrections = new LinkedHashMap<>();
        for (int i=0; i<exercisesWithSolutions.size(); i++) {
            ShuffledExerciseWithSolution exerciseWithSolution = exercisesWithSolutions.get(i);
            int userAnswer = userAnswers.get(i);
            if (exerciseWithSolution.getSolution() != userAnswer) {
                corrections.put(
                        exerciseWithSolution.getExercise().question(),
                        exerciseWithSolution.getExercise().answerChoices().get(exerciseWithSolution.getSolution()));
            }
        }
         return new QuizResults(score, corrections);
    }

    private record ProblemWithSolution(String question, String rightAnswer, String[] wrongAnswers) {}

    private static class ShuffledExerciseWithSolution {
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
