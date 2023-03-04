package org.doranco.models;

public class Trajet {


    private Long id;

    private String lieuDeDepart;

    private String lieuDArrive;

    private String dureeTrajet;

    private Double prix;

    private Enum statut;

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

    public void setStatut(Enum statut) {
        this.statut = statut;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
