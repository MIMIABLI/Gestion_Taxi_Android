package org.doranco.models;

public class Administrateur {

    private Long id;

    private  String login;

    private  String password;

    private String email;


    public Administrateur(Long id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;

    }

    public Administrateur() {
    }

}
