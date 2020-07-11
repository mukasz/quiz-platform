package com.mukasz.quizplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AnswerDTO {
    private String id;
    private String answerContent;
}
