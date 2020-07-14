package com.mukasz.quizplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class QuizPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizPlatformApplication.class, args);
    }

}
