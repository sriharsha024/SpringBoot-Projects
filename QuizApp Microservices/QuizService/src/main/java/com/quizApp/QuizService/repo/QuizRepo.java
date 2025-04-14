package com.quizApp.QuizService.repo;


import com.quizApp.QuizService.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo  extends JpaRepository<Quiz, Long> {
}
