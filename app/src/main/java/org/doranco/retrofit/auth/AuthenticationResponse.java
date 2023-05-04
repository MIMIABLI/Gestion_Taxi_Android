package org.doranco.retrofit.auth;

import org.doranco.models.UserType;

public class AuthenticationResponse {

    private String token;
    private UserType userType;
    private Long id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserType getUserType() {
        return userType;
    }

    public Long getId() {
        return id;
    }
}
