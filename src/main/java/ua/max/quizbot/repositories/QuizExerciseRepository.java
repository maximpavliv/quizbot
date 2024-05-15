package ua.max.quizbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.models.QuizExercise;

@Repository
public interface QuizExerciseRepository extends JpaRepository<QuizExercise, Long> {
}
