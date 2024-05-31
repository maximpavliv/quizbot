package ua.max.quizbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.model.QuizExercise;

@Repository
public interface QuizExerciseRepository extends JpaRepository<QuizExercise, Long> {
}
