package com.mukasz.quizplatform.security.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserToken {
    private Long id;
    private String username;
    private UserGroup group;
    private LocalDateTime expiresAt;
    private LocalDateTime issuedAt;
    private String issuer;
}
