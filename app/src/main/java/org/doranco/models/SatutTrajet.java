package org.doranco.models;

public enum SatutTrajet {

    EN_ATTENTE("en attente du chauffeur"),
    ENCOURS("en cours"),
    ANNULER("annuler la reservation"),
    A_DESTINATION("vous etes arrive");


    public String etat;

    SatutTrajet(String etat) {
        this.etat = etat;
    }
}
