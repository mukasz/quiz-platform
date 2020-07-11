package com.mukasz.quizplatform.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quiz_submissions")
public class QuizSubmition {
    @Id
    @GeneratedValue
    @Column(name = "quiz_submition_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private User participant;

    @OneToMany(mappedBy = "quizSubmition")
    private List<AnsweredQuestion> answeredQuestions;

    @Column(name = "passed")
    private Boolean passed;
}
