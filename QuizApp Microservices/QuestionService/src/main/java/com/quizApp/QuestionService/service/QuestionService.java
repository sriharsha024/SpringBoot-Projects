package com.quizApp.QuestionService.service;

import com.quizApp.QuestionService.model.Question;
import com.quizApp.QuestionService.model.QuestionWrapper;
import com.quizApp.QuestionService.model.Response;
import com.quizApp.QuestionService.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    public List<Question>getAllQuestions() {
        return questionRepo.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return questionRepo.findAllByCategory(category);
    }

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    public Question updateQuestion(Long id, Question question) {
        Optional<Question> existing = questionRepo.findById(id);
        if(existing.isPresent()) {
            Question question1 = existing.get();
            question1.setQuestion(question.getQuestion());
            question1.setOption1(question.getOption1());
            question1.setOption2(question.getOption2());
            question1.setOption3(question.getOption3());
            question1.setOption4(question.getOption4());
            question1.setAnswer(question.getAnswer());
            question1.setCategory(question.getCategory());
            question1.setDifficultyLevel(question.getDifficultyLevel());
            return questionRepo.save(question1);
        } else {
            throw new RuntimeException("Question with id " + id + " not found.");
        }
    }

    public boolean deleteQuestion(Long id) {
        if(questionRepo.findById(id).isPresent()) {
            questionRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Long> generateQuestions(String category, int numberOfQuestions) {
        List<Long> questions=questionRepo.findRandomQuestionsByCategory(category,numberOfQuestions);
        return questions;
    }

    public List<QuestionWrapper> getQuestionsByIds(List<Long> questionIds) {
        List<Question> questions=questionRepo.findAllById(questionIds);
        List<QuestionWrapper> questionWrappers=new ArrayList<>();
        for(Question q:questions) {
            QuestionWrapper question=new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWrappers.add(question);
        }
        return questionWrappers;
    }

    public Integer getScore(List<Response> responses) {

        int score = 0;
        for (Response r : responses) {
            Question question=questionRepo.findById(r.getId()).get();
            if(r.getResponse().equals(question.getAnswer())){
                score++;
            }
        }
        return score;
    }
}
