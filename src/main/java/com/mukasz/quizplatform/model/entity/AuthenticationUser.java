package com.mukasz.quizplatform.model.entity;

import com.mukasz.quizplatform.secutity.model.UserGroup;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "authentication_users")
@Data
@NoArgsConstructor
public class AuthenticationUser {
    @Id
    @GeneratedValue
    @Column(name = "authentication_user_id")
    private Long id;

    @OneToOne(mappedBy = "authenticationUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    private User user;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    public UserGroup[] getUserGroup() {
        return Arrays.stream(userGroup).map(UserGroup::valueOf).toArray(UserGroup[]::new);
    }

    public void setUserGroup(UserGroup[] userGroups) {
        this.userGroup = Arrays.stream(userGroups).map(UserGroup::toString).toArray(String[]::new);
    }

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "user_group")
    private String[] userGroup;

    @Column(name = "created_at")
    private Long createdAt = new Date().getTime();


    @Builder
    public AuthenticationUser(User user, String username, String passwordHash) {
        this.user = user;
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
