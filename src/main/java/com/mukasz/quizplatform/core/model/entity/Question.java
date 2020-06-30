package com.mukasz.quizplatform.core.model.entity;

import com.mukasz.quizplatform.core.model.QuestionType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private QuestionType type;

    @Column(name = "points")
    private Integer points;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @ManyToMany(mappedBy = "questionPool")
    private Set<Quiz> usedInQuizes;
}
