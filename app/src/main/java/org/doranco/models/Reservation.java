package org.doranco.models;

//import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {

    @SerializedName("idReservation")
    private Long id;

//    @SerializedName("client")
    private Client client;

//    @SerializedName("chauffeur")
    private Chauffeur chauffeur;

//    @SerializedName("date")
    private String date;

//    @SerializedName("heureDepart")
    private String heureDepart;

//    @SerializedName("heureArrive")
    private String heureArrive;

//    @SerializedName("statutResa")
    private StatutResa statutResa;

//    @SerializedName("trajet")
    private Trajet trajet;

    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrive() {
        return heureArrive;
    }

    public void setHeureArrive(String heureArrive) {
        this.heureArrive = heureArrive;
    }

    public StatutResa getStatut() {
        return statutResa;
    }

    public void setStatut(StatutResa statut) {
        this.statutResa = statut;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
}
