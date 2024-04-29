package ua.max.quizbot;

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

    public Quiz() {

        started = false;
        currentExerciseIdx = 0;
        userAnswers = new ArrayList<>();

        List<ProblemWithSolution> problemsWithSolutions = new ArrayList<>();
        problemsWithSolutions.add(new ProblemWithSolution(
                "Which of the following option leads to the portability and security of Java?",
                "Bytecode is executed by JVM",
                new String[]{"The applet makes the Java code secure and portable",
                        "Use of exception handling",
                        "Dynamic binding between objects"}
        ));
        problemsWithSolutions.add(new ProblemWithSolution(
                "Which of the following is not a Java features?",
                "Use of pointers",
                new String[]{"Dynamic",
                        "Architecture Neutral",
                        "Object-oriented"}
        ));
        problemsWithSolutions.add(new ProblemWithSolution(
                "The \\u0021 article referred to as a",
                "Unicode escape sequence",
                new String[]{"Octal escape",
                        "Hexadecimal",
                        "Line feed"}
        ));
        problemsWithSolutions.add(new ProblemWithSolution(
                "_____ is used to find and fix bugs in the Java programs.",
                "JDB",
                new String[]{"JVM",
                        "JRE",
                        "JDK"}
        ));
        problemsWithSolutions.add(new ProblemWithSolution(
                "What is the return type of the hashCode() method in the Object class?",
                "int",
                new String[]{"Object",
                        "long",
                        "void"}
        ));

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
