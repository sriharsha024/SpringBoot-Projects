package com.quizApp.QuizService.service;


import com.quizApp.QuizService.feign.QuizInterface;
import com.quizApp.QuizService.model.QuestionWrapper;
import com.quizApp.QuizService.model.Quiz;
import com.quizApp.QuizService.model.Response;
import com.quizApp.QuizService.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;


    public String createQuiz(String category, int noOfQuestions, String title) {
        List<Long> questions = quizInterface.generateQuestionsForQuiz(category,noOfQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);
        return "Success";
    }

    public List<QuestionWrapper> getQuizQuestions(long id) {
        Quiz optionalQuiz = quizRepo.findById(id).get();
        List<Long> questionIds = optionalQuiz.getQuestionIds();
        List<QuestionWrapper> questionWrappers=quizInterface.getQuestionsForQuiz(questionIds).getBody();

        return questionWrappers;

    }

    public Integer calculateScore(long id, List<Response> responses) {
        int score=quizInterface.calculateScore(responses).getBody();
        return score;
    }
}
