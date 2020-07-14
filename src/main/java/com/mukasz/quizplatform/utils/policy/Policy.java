package com.mukasz.quizplatform.utils.policy;

@FunctionalInterface
public interface Policy<T> {
    boolean check(T validationObject);
}
