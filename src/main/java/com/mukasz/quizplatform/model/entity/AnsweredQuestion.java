package com.mukasz.quizplatform.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "answered_questions")
public class AnsweredQuestion {
    @Id
    @GeneratedValue
    @Column(name = "answered_question_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submition_id")
    private QuizSubmition quizSubmition;

    // TODO: replace with ManyToMany
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    private Boolean score;
}
