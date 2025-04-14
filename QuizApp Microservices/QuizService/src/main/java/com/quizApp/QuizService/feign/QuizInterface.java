package com.quizApp.QuizService.feign;


import com.quizApp.QuizService.model.QuestionWrapper;
import com.quizApp.QuizService.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {
    //generate
    @GetMapping("/question/generate")
    public ResponseEntity<List<Long>> generateQuestionsForQuiz(@RequestParam String category, @RequestParam int numberOfQuestions);

    //get questions based on quizId
    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Long> questionIds);
    //get score
    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> responses);

}
