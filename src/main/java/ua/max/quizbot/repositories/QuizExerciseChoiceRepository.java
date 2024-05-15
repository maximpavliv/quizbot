package ua.max.quizbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.models.QuizExerciseChoice;

@Repository
public interface QuizExerciseChoiceRepository extends JpaRepository<QuizExerciseChoice, Long> {
}
