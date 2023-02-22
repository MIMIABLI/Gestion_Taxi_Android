package org.doranco.gesttion_reserv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.models.Chauffeur;
import org.doranco.retrofit.ClientApi;
import org.doranco.retrofit.RetrofitService;

import java.util.List;

public class CreationCompteChauffeur<ChauffeurApi> extends AppCompatActivity {
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
            String Deconnexion = String.valueOf(etDeconnexion.getText());
            String Motdepasse= String.valueOf(etMotdepasse.getText());
            String Date = String.valueOf(etDate.getText());
            String Demandedereservation = String.valueOf(etDemandedereservation.getText());
            String Voirmacourse = String.valueOf(etVoirmescourse.getText());

            Chauffeur chauffeur = new Chauffeur();
            chauffeur.setNom(nom);
            chauffeur.setPrenom(prenom);
            chauffeur.setCouleurDuVehicule(CouleurDuVehicule);
            chauffeur.setImmatriculationDuVehicule(ImmatriculationDuVehicule);*

            ChauffeurApi.saveChauffeur(chauffeur);

            @GetMapping("/all")
            public List<Chauffeur> getAllChauffeur(){
                List<Chauffeur> chauffeurList=chauffeurApi.findAll();
                return new List<>(chauffeurList, HttpStatus.OK);
            }

            @GetMapping("/find/{id}")
            public List<Chauffeur> getChauffeurById   {
                Chauffeur chauffeur=chauffeurApi.findChauffeurRById(id);
                return new List<>(chauffeur, HttpStatus.OK);
            }
            @PutMapping("/update")
            public List<Chauffeur> updateChauffeur(@RequestBody Chauffeur chauffeur){
                Chauffeur updateChauffeur=chauffeurService.update(chauffeur);
                return new Chauffeur<>(updateChauffeur,HttpStatus.OK);
            }
            @DeleteMapping("/delete/{id}")
            public List<Chauffeur> delete(reservationList){
                ChauffeurApi.delete(id);
                return new List<Chauffeur>(HttpStatus.OK);
            }

        });
    }
}