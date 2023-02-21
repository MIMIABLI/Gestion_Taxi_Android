package org.doranco.models;

import java.util.Date;

public class Reservation {

    private Long id;

    private Client client;

    private Chauffeur chauffeur;

    private Date date;

    private Date heureDepart;

    private Date heureArrive;

    private String statut;

    private Trajet trajet;

}
