package org.doranco.parcours.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.gesttion_reserv.R;
import org.doranco.parcours.client.Reservation;
import org.doranco.models.Chauffeur;
import org.doranco.retrofit.ChauffeurApi;
import org.doranco.retrofit.RetrofitService;

import java.util.List;

public class CreationCompteChauffeur extends AppCompatActivity {
    private RetrofitService retrofitService = new RetrofitService();
    private ChauffeurApi chauffeurApi;
    private Button creerCompte;
    private EditText etNom, etPrenom, etDeconnexion, etMotdepasse, etDate, etDemandedereservation, etVoirmescourse;
    private List<Reservation> reservationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_compte_chauffeur);
        //etnom = findViewById(R.Id.nom);
        chauffeurApi = retrofitService.getRetrofit().create(ChauffeurApi.class);
        creerCompte.setOnClickListener(view -> {
            String Nom = String.valueOf(etNom.getText());
            String Prenom = String.valueOf(etPrenom.getText());
            //String  = String.valueOf(et.getText());
            String Motdepasse= String.valueOf(etMotdepasse.getText());
            String Date = String.valueOf(etDate.getText());
           //List <Demandedereservation> = List.(etDemandedereservation.getText());
            //String Voirmacourse = String.valueOf(etVoirmescourse.getText());

            Chauffeur chauffeur = new Chauffeur();
           /* chauffeur.setNom(nom);
            chauffeur.setPrenom(prenom);
            chauffeur.setCouleurDuVehicule(CouleurDuVehicule);
            chauffeur.setImmatriculationDuVehicule(ImmatriculationDuVehicule);*/

            chauffeurApi.saveChauffeur(chauffeur);

        });
    }
}