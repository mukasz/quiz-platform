package com.mukasz.quizplatform.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mukasz.quizplatform.model.QuestionType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
@Entity
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

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @ManyToMany(mappedBy = "questionPool")
    private Set<Quiz> usedInQuizes;
}
