package com.mukasz.quizplatform.exception;

public class QPIllegalEntryException extends RuntimeException {
    public QPIllegalEntryException() {
    }

    public QPIllegalEntryException(String message) {
        super(message);
    }

    public QPIllegalEntryException(String message, Throwable cause) {
        super(message, cause);
    }

    public QPIllegalEntryException(Throwable cause) {
        super(cause);
    }
}
