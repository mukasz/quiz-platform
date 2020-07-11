package com.mukasz.quizplatform.dao;

import com.mukasz.quizplatform.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDAO extends JpaRepository<Answer, Long> {
}
