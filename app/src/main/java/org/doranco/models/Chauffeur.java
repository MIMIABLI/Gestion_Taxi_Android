package org.doranco.models;

import android.media.Image;
import android.widget.ImageView;

public class Chauffeur {

    private Long id;

    private String nom;

    private String prenom;

    private String login;

    private String password;

    private String email;

    private String telephone;

    private int photos;

    private String typeDeVehicules;

    private String couleurDuVehicule;

    private String immatriculationDuVehicule;

    private Double note;

    private String positionGPS;

    private Double Prix;
    //private List<Reservation> listReservation;


    public Chauffeur() {
    }

    public Double getPrix() {
        return Prix;
    }

    public void setPrix(Double prix) {
        Prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getPhotos() {
        return photos;
    }

    public void setPhotos(int photos) {
        this.photos = photos;
    }

    public String getTypeDeVehicules() {
        return typeDeVehicules;
    }

    public void setTypeDeVehicules(String typeDeVehicules) {
        this.typeDeVehicules = typeDeVehicules;
    }

    public String getCouleurDuVehicule() {
        return couleurDuVehicule;
    }

    public void setCouleurDuVehicule(String couleurDuVehicule) {
        this.couleurDuVehicule = couleurDuVehicule;
    }

    public String getImmatriculationDuVehicule() {
        return immatriculationDuVehicule;
    }

    public void setImmatriculationDuVehicule(String immatriculationDuVehicule) {
        this.immatriculationDuVehicule = immatriculationDuVehicule;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public String getPositionGPS() {
        return positionGPS;
    }

    public void setPositionGPS(String positionGPS) {
        this.positionGPS = positionGPS;
    }
}
