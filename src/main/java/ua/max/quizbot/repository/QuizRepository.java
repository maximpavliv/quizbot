package ua.max.quizbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
