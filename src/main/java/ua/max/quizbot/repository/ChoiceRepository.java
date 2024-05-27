package ua.max.quizbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.model.Choice;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
