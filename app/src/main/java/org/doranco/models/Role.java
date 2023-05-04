package org.doranco.models;

//import com.google.gson.annotations.JsonAdapter;
//import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public enum Role implements Serializable {

//    @SerializedName("USER")
    USER("USER"),

//    @SerializedName("ADMIN")
    ADMIN("ADMIN");

   public String userRole;

    Role(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
