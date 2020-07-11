package com.mukasz.quizplatform.controller.error;

import com.mukasz.quizplatform.exception.QPNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(QPNotFoundException.class)
    public ResponseEntity<QPApiError> resourceNotFound(QPNotFoundException ex, WebRequest request) {
        QPApiError apiError = QPApiError.builder()
                .message("Requested resource not found")
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
