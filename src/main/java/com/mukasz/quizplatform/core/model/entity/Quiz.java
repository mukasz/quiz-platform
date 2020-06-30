package com.mukasz.quizplatform.core.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "quizes")
public class Quiz {
    @Id
    @GeneratedValue
    @Column(name = "quiz_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @ManyToMany( cascade = { CascadeType.ALL } )
    @JoinTable(
            name = "quiz_participations",
            joinColumns = { @JoinColumn(name = "quiz_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> participants;

    @ManyToMany( cascade = { CascadeType.ALL } )
    @JoinTable(
            name = "quiz_questions",
            joinColumns = { @JoinColumn(name = "quiz_id") },
            inverseJoinColumns = { @JoinColumn(name = "question_id") }
    )
    private Set<Question> questionPool;

    private Integer questionsCount;
}
