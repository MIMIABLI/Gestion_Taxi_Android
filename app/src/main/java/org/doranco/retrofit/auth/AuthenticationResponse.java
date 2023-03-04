package org.doranco.retrofit.auth;

import org.doranco.models.UserType;

public class AuthenticationResponse {

    private String token;
    private UserType userType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserType getUserType() {
        return userType;
    }

}
