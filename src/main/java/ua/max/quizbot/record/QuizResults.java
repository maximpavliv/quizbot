package ua.max.quizbot.record;

import java.util.SequencedMap;

public record QuizResults(String score, SequencedMap<String, String> corrections) {
}
