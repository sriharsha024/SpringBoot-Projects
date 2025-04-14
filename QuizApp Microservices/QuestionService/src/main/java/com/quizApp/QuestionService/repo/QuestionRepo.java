package com.quizApp.QuestionService.repo;

import com.quizApp.QuestionService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

    List<Question> findAllByCategory(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<Long> findRandomQuestionsByCategory(String category, int limit);

}
