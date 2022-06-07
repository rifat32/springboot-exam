package com.example.demo.controllers;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;

import com.example.demo.services.QuestionService;
import com.example.demo.view.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class QuestionController {
private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping({"/questions"})
    public Response createQuestion(@RequestBody Question question) {
    return questionService.createQuestion(question);
}
    @GetMapping({"/questions"})
    public Response getQuestions() {
        return questionService.getQuestions();
    }
    @GetMapping({"/questions/{questionId}"})
    public Response getQuestionById(@PathVariable("questionId") Integer questionId) {
        return questionService.getQuestionById(questionId);
    }


@PostMapping({"/answers/{questionId}"})
public Response createAnswer(@RequestBody Answer answer,@PathVariable("questionId") Integer questionId) {
    return questionService.createAnswer(answer,questionId);
}
    @PostMapping({"/solutions/{questionId}"})
    public Response createSolution(@RequestBody Answer answer,@PathVariable("questionId") Integer questionId) {
        return questionService.createSolution(answer,questionId);
    }



}
