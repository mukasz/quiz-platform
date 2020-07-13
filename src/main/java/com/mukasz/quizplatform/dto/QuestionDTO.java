package com.mukasz.quizplatform.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mukasz.quizplatform.model.QuestionType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@ApiModel(value = "Question")
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties
public class QuestionDTO {
    private Long id;
    private String title;
    private List<AnswerDTO> answers;
    private List<String> correctAnswers;
    private Integer points;
}
