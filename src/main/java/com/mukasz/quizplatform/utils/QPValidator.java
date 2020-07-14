package com.mukasz.quizplatform.utils;

@FunctionalInterface
public interface QPValidator<T> {
    boolean validate(T object);
}
