package ua.max.quizbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
