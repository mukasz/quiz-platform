package com.mukasz.quizplatform.secutity.model;

public enum UserGroup {
    SYS_ADMIN("SYS_ADMIN"),
    TEST_SUPERVISOR("TEST_SUPERVISOR"),
    TEST_PARTICIPANT("TEST_PARTICIPANT");

    public final String label;

    UserGroup(String label) {
        this.label = label;
    }
}
