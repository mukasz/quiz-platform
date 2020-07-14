package com.mukasz.quizplatform.controller;

import com.mukasz.quizplatform.secutity.dto.AuthenticationResponse;
import com.mukasz.quizplatform.secutity.dto.SignInDTO;
import com.mukasz.quizplatform.secutity.dto.SignUpDTO;
import com.mukasz.quizplatform.secutity.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

@Api(value = "Authentication", tags = { "Authentication" }, description="API for handling authentication" )
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @ApiOperation(value = "Returns JWT created for user with provided authData")
    @PostMapping("/sign-in")
    public AuthenticationResponse signIn(@RequestBody SignInDTO authData) {
        return authenticationService.signIn(authData);
    }

    @ApiOperation(value = "Creates new user based on provided authData, then returns JWT created for that user")
    @ApiResponses({
            @ApiResponse(code = 409, message = "User Already exists"),
            @ApiResponse(code = 400, message = "Password or user do not meet requirements")
    })
    @PostMapping("/sign-up")
    public AuthenticationResponse signUp(@RequestBody SignUpDTO authData) {
        return authenticationService.signUp(authData);
    }
}
