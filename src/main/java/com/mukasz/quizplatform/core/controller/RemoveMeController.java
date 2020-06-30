package com.mukasz.quizplatform.core.controller;

import com.mukasz.quizplatform.core.dao.UserDAO;
import com.mukasz.quizplatform.core.model.entity.User;
import com.mukasz.quizplatform.security.model.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class RemoveMeController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping
    public User addUser() {
        User newUser = User.builder()
                .userGroup(UserGroup.SYS_ADMIN)
                .passwordHash("123123123124fwesff23r23")
                .username("USER")
                .build();

        return userDAO.save(newUser);
    }
}
