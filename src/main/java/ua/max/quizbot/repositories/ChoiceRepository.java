package ua.max.quizbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.models.Choice;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
