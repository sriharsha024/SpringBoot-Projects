package com.quizApp.QuestionService.controller;

import com.quizApp.QuestionService.model.Question;
import com.quizApp.QuestionService.model.QuestionWrapper;
import com.quizApp.QuestionService.model.Response;
import com.quizApp.QuestionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        if(questionService.getAllQuestions() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) {
        if (questionService.getAllQuestionsByCategory(category)== null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questionService.getAllQuestionsByCategory(category), HttpStatus.OK);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id,@RequestBody Question question) {
        if(questionService.updateQuestion(id, question)== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(questionService.updateQuestion(id,question),HttpStatus.OK);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        if(questionService.deleteQuestion(id)==true){
            return new ResponseEntity<>("Question deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Question not found with id "+id,HttpStatus.NOT_FOUND);
    }

    //generate
    @GetMapping("/generate")
    public ResponseEntity<List<Long>> generateQuestionsForQuiz(@RequestParam String category, @RequestParam int numberOfQuestions) {
        System.out.println(environment.getProperty("local.server.port"));
        return new ResponseEntity<>(questionService.generateQuestions(category,numberOfQuestions), HttpStatus.OK);
    }

    //get questions based on quizId
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Long> questionIds) {
        System.out.println(environment.getProperty("local.server.port"));
        return new ResponseEntity<>(questionService.getQuestionsByIds(questionIds),HttpStatus.OK);
    }

    //get score
    @PostMapping("/getScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses) {
        System.out.println(environment.getProperty("local.server.port"));
        return new ResponseEntity<>(questionService.getScore(responses),HttpStatus.OK);
    }

}
