package ua.max.quizbot.records;

import java.util.SequencedMap;

public record QuizResults(String score, SequencedMap<Integer, String> corrections) {
}
