package com.quizApp.QuestionService.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Response {
    private long id;
    private String response;
}
