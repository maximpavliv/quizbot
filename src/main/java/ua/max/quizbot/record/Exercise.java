package ua.max.quizbot.record;

import java.util.List;

public record Exercise(Integer questionIndex, String question, List<String> answerChoices) {
}
