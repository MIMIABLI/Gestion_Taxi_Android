package org.doranco.models;

//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Chauffeur implements Serializable {

    @SerializedName("idChauffeur")
    private Long id;

//    @SerializedName("nom")
    private String nom;

//    @SerializedName("prenom")
    private String prenom;

//    @SerializedName("login")
    private String login;

//    @SerializedName("password")
    private String password;

//    @SerializedName("email")
    private String email;

//    @SerializedName("telephone")
    private String telephone;

//    @SerializedName("photos")
    private int photos;

//    @SerializedName("typeDeVehicules")
    private String typeDeVehicules;

//    @SerializedName("couleurDuVehicule")
    private String couleurDuVehicule;

//    @SerializedName("immatriculationDuVehicule")
    private String immatriculationDuVehicule;

//    @SerializedName("note")
    private Double note;

//    @SerializedName("secteur")
    private String secteur;

//    @SerializedName("prix")
    private Double prix;

//   @SerializedName("role")
    private Role role;

//    @SerializedName("listReservation")
    private List<Reservation> listReservation;


    public Chauffeur() {
        this.role = Role.USER;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        prix = prix;
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

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public List<Reservation> getListReservation() {
        return listReservation;
    }

    public void setListReservation(List<Reservation> listReservation) {
        this.listReservation = listReservation;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
