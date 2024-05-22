package ua.max.quizbot.records;

import java.util.List;

public record Exercise(Integer questionIndex, String question, List<String> answerChoices) {
}
