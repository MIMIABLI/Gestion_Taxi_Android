package org.doranco.models;

//import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trajet implements Serializable {


    @SerializedName("idTrajet")
    private Long trajet_id;
    //    @SerializedName("secteur")
    private String secteur;
    //    @SerializedName("lieuDeDepart")
    private String lieuDeDepart;
    //    @SerializedName("lieuDArrive")
    private String lieuDArrive;
    //    @SerializedName("duree")
    private String duree;
    //    @SerializedName("prix")
    private Double prix;
    //    @SerializedName("statut")
    private StatutTrajet statut;
    //    @SerializedName("reservation")
    private Reservation reservation;

    public Trajet() {
    }

    public Long getId() {
        return trajet_id;
    }

    public void setId(Long id) {
        this.trajet_id = id;
    }

    public String getLieuDeDepart() {
        return lieuDeDepart;
    }

    public void setLieuDeDepart(String lieuDeDepart) {
        this.lieuDeDepart = lieuDeDepart;
    }

    public String getLieuDArrive() {
        return lieuDArrive;
    }

    public void setLieuDArrive(String lieuDArrive) {
        this.lieuDArrive = lieuDArrive;
    }

    public String getDureeTrajet() {
        return duree;
    }

    public void setDureeTrajet(String duree) {
        this.duree = duree;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Enum getStatut() {
        return statut;
    }

    public void setStatut(StatutTrajet statut) {
        this.statut = statut;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }
}
