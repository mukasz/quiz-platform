package com.mukasz.quizplatform.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;
    private String internalId;
    private String answerContent;
    private Boolean isCorrect;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="question_id")
    private Question question;

    @OneToMany(mappedBy = "answer")
    private Set<AnsweredQuestion> answeredQuestion;
}
