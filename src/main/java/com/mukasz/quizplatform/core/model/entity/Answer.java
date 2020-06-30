package com.mukasz.quizplatform.core.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;
    private String answerContent;
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @OneToMany(mappedBy = "answer")
    private Set<AnsweredQuestion> answeredQuestion;
}
