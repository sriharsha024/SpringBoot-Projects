package com.projects.QuizApp.repo;

import com.projects.QuizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo  extends JpaRepository<Quiz, Long> {
}
