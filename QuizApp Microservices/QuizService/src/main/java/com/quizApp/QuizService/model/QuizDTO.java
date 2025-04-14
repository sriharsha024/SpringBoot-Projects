package com.quizApp.QuizService.model;

import lombok.Data;

@Data
public class QuizDTO {

    private String category;
    private int noOfQuestions;
    private String title;
}
