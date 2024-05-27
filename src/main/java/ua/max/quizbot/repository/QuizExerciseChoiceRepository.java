package ua.max.quizbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.model.QuizExerciseChoice;

@Repository
public interface QuizExerciseChoiceRepository extends JpaRepository<QuizExerciseChoice, Long> {
}
