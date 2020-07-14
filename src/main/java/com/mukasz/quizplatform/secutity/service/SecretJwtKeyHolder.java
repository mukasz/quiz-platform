package com.mukasz.quizplatform.secutity.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.security.Key;


/**
 * Holder of the signing key. After restarting application key is being changeg and all tokens get expired
 */
@Getter
@Component
public class SecretJwtKeyHolder {
    private Key key;

    @PostConstruct
    private void generateKey() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

}
