package org.doranco.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum StatutResa implements Serializable {

    @SerializedName("VALIDEE")
    VALIDEE("VALIDEE"),
    @SerializedName("REFUSEE")
    REFUSEE("REFUSEE"),
    @SerializedName("EN_ATTENTE")
    EN_ATTENTE("EN_ATTENTE");

    private String statutResaString;

    StatutResa(String statutResaString) {
        this.statutResaString = statutResaString;
    }

    public String getStatutResaString() {
        return statutResaString;
    }
}
