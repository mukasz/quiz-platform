package com.mukasz.quizplatform.secutity.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "SignInData")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTO {
    private String username;
    private String password;
}
