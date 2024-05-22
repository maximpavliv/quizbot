package ua.max.quizbot.records;

import java.util.List;

public record Exercise(Integer questionIndex, String question, ExerciseSnippet snippet, List<String> answerChoices) {
}
