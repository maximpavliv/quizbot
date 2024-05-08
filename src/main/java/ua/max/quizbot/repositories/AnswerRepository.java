package ua.max.quizbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.models.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
