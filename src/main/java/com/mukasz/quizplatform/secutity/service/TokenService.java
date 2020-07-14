package com.mukasz.quizplatform.secutity.service;

import com.mukasz.quizplatform.model.entity.AuthenticationUser;
import com.mukasz.quizplatform.secutity.JWTProperties;
import com.mukasz.quizplatform.secutity.utils.JWTClaimsNames;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    private final SecretJwtKeyHolder secretJwtKeyHolder;
    private final JWTProperties jwtProperties;

    @Autowired
    public TokenService(SecretJwtKeyHolder secretJwtKeyHolder, JWTProperties jwtProperties) {
        this.secretJwtKeyHolder = secretJwtKeyHolder;
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(AuthenticationUser authenticationUser) {
        return Jwts.builder()
                .claim(JWTClaimsNames.USERNAME, authenticationUser.getUsername())
                .claim(JWTClaimsNames.GROUPS, authenticationUser.getUserGroup())
                .setSubject(authenticationUser.getId().toString())
                .setIssuer(jwtProperties.getIssuer())
                .setAudience(jwtProperties.getAudience())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(secretJwtKeyHolder.getKey())
                .setId(UUID.randomUUID().toString())
                .compact();
    }

    private Date generateExpirationDate() {
        Instant instant = Instant.now()
                .plusSeconds(jwtProperties.getValidityTime());
        return new Date(instant.toEpochMilli());
    }
}
