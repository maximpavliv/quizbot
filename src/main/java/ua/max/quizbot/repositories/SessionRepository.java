package ua.max.quizbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.models.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByChatId(Long chatId);
}
