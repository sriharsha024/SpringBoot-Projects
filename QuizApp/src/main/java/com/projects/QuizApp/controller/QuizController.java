package com.projects.QuizApp.controller;

import com.projects.QuizApp.model.Question;
import com.projects.QuizApp.model.QuestionWrapper;
import com.projects.QuizApp.model.Response;
import com.projects.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<List<Question>> createQuiz(
            @RequestParam String category,
            @RequestParam int noOfQuestions,
            @RequestParam String title) {
        return new ResponseEntity<>(quizService.createQuiz(category, noOfQuestions, title), HttpStatus.CREATED);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable long id, @RequestBody List<Response> responses ){
        return new ResponseEntity<>( quizService.calculateScore(id, responses),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable long id) {
        List<QuestionWrapper> questions = quizService.getQuizQuestions(id);
        if (questions == null || questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

}
