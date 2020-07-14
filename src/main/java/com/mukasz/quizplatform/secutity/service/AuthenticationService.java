package com.mukasz.quizplatform.secutity.service;

import com.mukasz.quizplatform.dao.AuthenticationUserDAO;
import com.mukasz.quizplatform.exception.QPIllegalEntryException;
import com.mukasz.quizplatform.secutity.dto.AuthenticationResponse;
import com.mukasz.quizplatform.secutity.dto.SignInDTO;
import com.mukasz.quizplatform.secutity.dto.SignUpDTO;
import com.mukasz.quizplatform.secutity.model.UserGroup;
import com.mukasz.quizplatform.model.entity.AuthenticationUser;
import com.mukasz.quizplatform.model.entity.User;
import com.mukasz.quizplatform.secutity.utils.validator.SignUpValidator;
import com.mukasz.quizplatform.utils.QPValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class AuthenticationService {

    private AuthenticationUserDAO authenticationUserDAO;
    private TokenService tokenService;

    public AuthenticationService(AuthenticationUserDAO authenticationUserDAO,
                                 TokenService tokenService) {
        this.authenticationUserDAO = authenticationUserDAO;
        this.tokenService = tokenService;
    }

    //TODO: password validation
    public AuthenticationResponse signUp(SignUpDTO signUpDTO) {
        QPValidator<SignUpDTO> validator = SignUpValidator.permitAllValidator();
        if(!validator.validate(signUpDTO)) {
            throw new QPIllegalEntryException("Password or username do not match security policy");
        }

        log.info("Creating new user with username: {}", signUpDTO.getUsername());
        AuthenticationUser authenticationUser = AuthenticationUser.builder()
                .passwordHash(hashPassword(signUpDTO.getPassword()))
                .username(signUpDTO.getUsername())
                .build();

        User user = User.builder()
                .displayedName(signUpDTO.getDisplayedName())
                .authenticationUser(authenticationUser)
                .build();

        authenticationUser.setUserGroup(new UserGroup[] {UserGroup.TEST_PARTICIPANT, UserGroup.TEST_SUPERVISOR});
        authenticationUser.setUser(user);
        authenticationUser = authenticationUserDAO.save(authenticationUser);

        return AuthenticationResponse
                .signUpOk(tokenService.generateToken(authenticationUser));
    }

    public AuthenticationResponse signIn(SignInDTO signInDTO) {
        return authenticationUserDAO
                .findAuthenticationUserByUsernameAndPasswordHash(signInDTO.getUsername(), signInDTO.getPassword())
                .map(user -> tokenService.generateToken(user))
                .map(AuthenticationResponse::ok)
                .orElse(AuthenticationResponse.badCredentials());
    }

    //TODO: implement in future
    private String hashPassword(String pass) {
        return pass;
    }
}
