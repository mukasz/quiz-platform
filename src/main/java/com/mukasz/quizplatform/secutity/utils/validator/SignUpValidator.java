package com.mukasz.quizplatform.secutity.utils.validator;

import com.mukasz.quizplatform.secutity.dto.SignUpDTO;
import com.mukasz.quizplatform.utils.QPValidator;
import com.mukasz.quizplatform.utils.policy.PermitAllPolicy;
import com.mukasz.quizplatform.utils.policy.Policy;
import lombok.Setter;
import java.util.List;

@Setter
public class SignUpValidator implements QPValidator<SignUpDTO> {
    @Override
    public boolean validate(SignUpDTO object) {
        return usernamePolicy.stream().allMatch(policy -> policy.check(object.getUsername())) &&
                passwordPolicy.stream().allMatch(policy -> policy.check(object.getPassword()));
    }

    private List<Policy<String>> usernamePolicy;
    private List<Policy<String>> passwordPolicy;

    public static SignUpValidator permitAllValidator() {
        SignUpValidator signUpValidator = new SignUpValidator();
        signUpValidator.setPasswordPolicy(List.of(new PermitAllPolicy<>()));
        signUpValidator.setUsernamePolicy(List.of(new PermitAllPolicy<>()));
        return signUpValidator;
    }
}
