package com.project.domain;

public class Member {
    private Long id;
    private String auth;
    private String password;

    public Long getId() {
        return id;
    }

    public String getAuth() {
        return auth;
    }

    public String getPassword() {
        return password;
    }

    public Member(Long id, String auth, String password) {
        this.id = id;
        this.auth = auth;
        this.password = password;
    }
}
