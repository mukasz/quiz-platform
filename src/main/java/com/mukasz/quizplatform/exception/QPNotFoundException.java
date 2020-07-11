package com.mukasz.quizplatform.exception;

public class QPNotFoundException extends RuntimeException {

    public QPNotFoundException(String message) {
        super(message);
    }

    public QPNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public QPNotFoundException(Throwable cause) {
        super(cause);
    }
}
