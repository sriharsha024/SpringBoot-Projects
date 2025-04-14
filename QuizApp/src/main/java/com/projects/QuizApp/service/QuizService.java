package com.projects.QuizApp.service;

import com.projects.QuizApp.model.Question;
import com.projects.QuizApp.model.QuestionWrapper;
import com.projects.QuizApp.model.Quiz;
import com.projects.QuizApp.model.Response;
import com.projects.QuizApp.repo.QuestionRepo;
import com.projects.QuizApp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;


    public List<Question> createQuiz(String category, int noOfQuestions, String title) {

        List<Question> questions=questionRepo.findRandomQuestionsByCategory(category,noOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);

        return questions;
    }

    public List<QuestionWrapper> getQuizQuestions(long id) {
        Optional<Quiz> optionalQuiz = quizRepo.findById(id);

        if (optionalQuiz.isPresent()) {
            Quiz quiz = optionalQuiz.get();
            List<Question> questionsFromDB = quiz.getQuestions();

            List<QuestionWrapper> questionWrappers = new ArrayList<>();
            for (Question q : questionsFromDB) {
                QuestionWrapper wrapper = new QuestionWrapper(
                        q.getId(), q.getQuestion(), q.getOption1(),
                        q.getOption2(), q.getOption3(), q.getOption4()
                );
                questionWrappers.add(wrapper);
            }
            return questionWrappers;
        }
        return null;

    }

    public Integer calculateScore(long id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questionsFromDB = quiz.getQuestions();
        int score = 0;
        int i=0;
        for (Response r : responses) {
            if(r.getResponse().equals(questionsFromDB.get(i).getAnswer())){
                score++;
            }
            i++;
        }
        return score;
    }
}
