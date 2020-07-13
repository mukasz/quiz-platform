package com.mukasz.quizplatform.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@ApiModel(value = "Answer")
@Data
@Builder
@AllArgsConstructor
public class AnswerDTO {
    private String id;
    private String answerContent;
}
