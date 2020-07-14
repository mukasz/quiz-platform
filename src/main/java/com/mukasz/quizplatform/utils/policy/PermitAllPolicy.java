package com.mukasz.quizplatform.utils.policy;

public class PermitAllPolicy<T> implements Policy<T> {
    @Override
    public boolean check(T validationObject) {
        return true;
    }
}
