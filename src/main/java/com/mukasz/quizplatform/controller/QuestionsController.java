package com.mukasz.quizplatform.controller;

import com.mukasz.quizplatform.dto.QuestionDTO;
import com.mukasz.quizplatform.model.entity.Question;
import com.mukasz.quizplatform.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Question", tags = { "Question" }, description="API for creating and getting questions" )
@RestController
@RequestMapping("/questions")
public class QuestionsController {

    private QuestionService questionService;

    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // TODO: ADD sort and pagination
    @ApiOperation(value = "Get list of all questions", tags = "Question")
    @GetMapping
    public List<QuestionDTO> getQuestions() {
        return questionService.getAllQuestions();
    }

    @ApiOperation(value = "Get single question by ID", tags = "Question")
    @GetMapping("/{id}")
    public QuestionDTO getQuestion(@PathVariable("id") Long id) {
        return questionService.getQuestionById(id);
    }

    @ApiOperation(value = "Add new question")
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public QuestionDTO createQuestion(@RequestBody QuestionDTO question) {
        return questionService.createQuestion(question);
    }

    @ApiOperation(value = "Add list of questions", responseReference = "List of ids of added questions")
    @PostMapping("/list")
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<Long> createManyQuestions(@RequestBody List<QuestionDTO> questions) {
        return questionService.createManyQuestions(questions);
    }

}
