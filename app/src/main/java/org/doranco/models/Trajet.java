package org.doranco.models;

import androidx.annotation.InspectableProperty;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trajet implements Serializable {


    @SerializedName("trajet_id")
    private Long id;
    @SerializedName("secteur")
    private String secteur;
    @SerializedName("lieuDeDepart")
    private String lieuDeDepart;
    @SerializedName("lieuDArrive")
    private String lieuDArrive;
    @SerializedName("duree")
    private String dureeTrajet;
    @SerializedName("prix")
    private Double prix;
    @SerializedName("statut")
    private StatutTrajet statut;
    @SerializedName("reservation")
    private Reservation reservation;

    public Trajet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return dureeTrajet;
    }

    public void setDureeTrajet(String dureeTrajet) {
        this.dureeTrajet = dureeTrajet;
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
