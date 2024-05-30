package ua.max.quizbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.max.quizbot.model.Snippet;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Long> {
}
