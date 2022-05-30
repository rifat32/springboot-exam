package com.example.demo.services;


import com.example.demo.models.Answer;
import com.example.demo.models.Question;

import com.example.demo.repositories.AnswerRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.view.Response;
import com.example.demo.view.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;


    public QuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {

        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    public Response createQuestion(Question  question) {
        return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, "Question Created Successfully", questionRepository.save(question));
    }
    public Response getQuestions() {
        List<Question> quetions = questionRepository.findAll();
        for (int i = 0; i< quetions.size(); i++) {
            quetions.get(i).setSolution(null);

        }

        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Questions List", quetions);
    }
    public Response getQuestionById(Integer questionId) {
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Questions List", questionRepository.findById(questionId));
    }

    public Response createAnswer(Answer answer , Integer questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        Question  updatableQuestion = question.get();
        if(!Objects.isNull(updatableQuestion)) {
            updatableQuestion.getAnswers().add(answer);
            return  ResponseBuilder.getSuccessResponse(HttpStatus.CREATED,"Answer Created Successfully",questionRepository.save(updatableQuestion)) ;
        }
        else {
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND,"No Questions found");
        }
    }
    public Response createSolution(Answer answer , Integer questionId) {
        Optional<Question> question = questionRepository.findById(questionId);
        Question  updatableQuestion = question.get();
        if(!Objects.isNull(updatableQuestion)) {
            updatableQuestion.getAnswers().add(answer);
            updatableQuestion.setSolution(answer);
            return  ResponseBuilder.getSuccessResponse(HttpStatus.CREATED,"Solution Created Successfully",questionRepository.save(updatableQuestion)) ;
        }
        else {
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND,"No Questions found");
        }
    }
}
