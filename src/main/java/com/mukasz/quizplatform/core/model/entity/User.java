package com.mukasz.quizplatform.core.model.entity;

import com.mukasz.quizplatform.security.model.UserGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "user_group")
    private UserGroup userGroup;

    @ManyToMany(mappedBy = "participants")
    private Set<Quiz> participatedQuizes;

    @OneToMany(mappedBy = "supervisor")
    private Set<Quiz> supervisedQuizes;
}
