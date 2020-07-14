package com.mukasz.quizplatform.dao;

import com.mukasz.quizplatform.model.entity.AuthenticationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationUserDAO extends JpaRepository<AuthenticationUser, Long> {
    Optional<AuthenticationUser> findAuthenticationUserByUsernameAndPasswordHash(String username, String passwordHash);
}
