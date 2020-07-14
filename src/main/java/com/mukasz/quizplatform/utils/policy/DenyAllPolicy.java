package com.mukasz.quizplatform.utils.policy;

public class DenyAllPolicy<T> implements Policy<T> {
    @Override
    public boolean check(T validationObject) {
        return false;
    }
}
