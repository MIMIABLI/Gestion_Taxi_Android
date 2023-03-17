package org.doranco.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum UserType implements Serializable {

    @SerializedName("CLIENT")
    CLIENT("CLIENT"),
    @SerializedName("CHAUFFEUR")
    CHAUFFEUR("CHAUFFEUR"),
    @SerializedName("ADMIN")
    ADMIN("ADMIN");

    private String userTypeString;

    UserType(String userTypeString) {
        this.userTypeString = userTypeString;
    }

    public String getUserTypeString() {
        return userTypeString;
    }
}
