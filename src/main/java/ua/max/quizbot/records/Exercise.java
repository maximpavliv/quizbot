package ua.max.quizbot.records;

import java.util.List;

public record Exercise(String question, List<String> answerChoices) {
}
