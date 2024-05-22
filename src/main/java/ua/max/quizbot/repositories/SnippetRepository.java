package ua.max.quizbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.models.Snippet;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Long> {
}
