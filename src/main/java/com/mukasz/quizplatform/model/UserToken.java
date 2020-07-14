package com.mukasz.quizplatform.model;

import com.mukasz.quizplatform.secutity.model.UserGroup;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserToken {
    private Long id;
    private String username;
    private UserGroup[] group;
    private LocalDateTime expiresAt;
    private LocalDateTime issuedAt;
    private String issuer;
}
