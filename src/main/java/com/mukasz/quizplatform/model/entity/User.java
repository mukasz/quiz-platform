package com.mukasz.quizplatform.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "displayed_name")
    private String displayedName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authentication_user_id")
    @JsonIgnoreProperties
    private AuthenticationUser authenticationUser;

    @ManyToMany(mappedBy = "participants")
    private Set<Quiz> participatedQuizes;

    @OneToMany(mappedBy = "supervisor")
    private Set<Quiz> supervisedQuizes;
}
