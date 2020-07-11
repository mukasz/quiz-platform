package com.mukasz.quizplatform.controller.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.datetime.joda.JodaTimeContext;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class QPApiError {
    private final String timestamp = new Date().toString();
    private final String path;
    private final String message;
}
