package org.doranco.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public enum StatutTrajet implements Serializable {

    @SerializedName("EN_ATTENTE")
    EN_ATTENTE("en attente du chauffeur"),
    @SerializedName("ENCOURS")
    ENCOURS("en cours"),
    @SerializedName("ANNULER")
    ANNULER("annuler la reservation"),
    @SerializedName("A_DESTINATION")
    A_DESTINATION("vous etes arrive");


    public String etat;

    StatutTrajet(String etat) {
        this.etat = etat;
    }
}
