package com.mukasz.quizplatform.secutity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {

    @ApiModelProperty(value = "Authentication success flag")
    private boolean isAuthenticated;

    @ApiModelProperty(value = "Detailed infromation about authentication result")
    private String message;

    @ApiModelProperty(value = "If authenticated JWT token value else null")
    private String token;

    public static AuthenticationResponse ok(String token) {
        return new AuthenticationResponse(true,
                "Authentication success",
                token);
    }

    public static AuthenticationResponse badCredentials() {
        return new AuthenticationResponse(false,
                "Wrong username or password",
                null);

    }

    public static AuthenticationResponse signUpOk(String token) {
        return new AuthenticationResponse(true,
                "New user signed up",
                token);
    }
}
