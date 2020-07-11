package com.mukasz.quizplatform.controller;

import com.mukasz.quizplatform.dto.QuestionDTO;
import com.mukasz.quizplatform.model.entity.Question;
import com.mukasz.quizplatform.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private QuestionService questionService;

    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // TODO: ADD sort and pagination
    @GetMapping
    public List<QuestionDTO> getQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestion(@PathVariable("id") Long id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping
    public Question createQuestion(@RequestBody QuestionDTO question) {
        return questionService.createQuestion(question);
    }

    @PostMapping("/list")
    public List<Long> createManyQuestions(@RequestBody List<QuestionDTO> questions) {
        return questionService.createManyQuestions(questions);
    }

}
