package org.doranco.parcours.admin;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ChauffeurApi;

public class CreationCompteChauffeurEtape2 extends AppCompatActivity {

    private RetrofitService retrofitService = new RetrofitService();
    private ChauffeurApi chauffeurApi;
    private Chauffeur chauffeur;
    private Button creationCompteChauffeur, retour;
    private EditText marqueDuVehicule, couleurDuVehicule, immatriculationDuVehicule, secteurDuChauffeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chauffeur_creation_compte_etape_2);

        creationCompteChauffeur = findViewById(R.id.creerCompteBtn);
        retour = findViewById(R.id.retourEtapeCreerCompteChauffeur);

        marqueDuVehicule = findViewById(R.id.marqueEtSerieDuVehicule);
        couleurDuVehicule = findViewById(R.id.couleurDuVehicule);
        immatriculationDuVehicule = findViewById(R.id.immatriculationDuVehicule);
        secteurDuChauffeur = findViewById(R.id.secteurDuCHauffeur);

        chauffeurApi = retrofitService.getRetrofit().create(ChauffeurApi.class);
        chauffeur = (Chauffeur) getIntent().getSerializableExtra("chauffeur");

        onClickCreerCompteChauffeurValidation();
        onClickRetourEtapePrecedente();

    }

    private void onClickCreerCompteChauffeurValidation() {
        creationCompteChauffeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marqueVehicule = String.valueOf(marqueDuVehicule.getText());
                String couleurVehicule = String.valueOf(couleurDuVehicule.getText());
                String immatriculation = String.valueOf(immatriculationDuVehicule.getText());
                String secteur = String.valueOf(secteurDuChauffeur.getText());

                chauffeur.setTypeDeVehicules(marqueVehicule);
                chauffeur.setCouleurDuVehicule(couleurVehicule);
                chauffeur.setImmatriculationDuVehicule(immatriculation);
                chauffeur.setSecteur(secteur);

                if (chauffeur != null) {
                    chauffeurApi.saveChauffeur(chauffeur);
                    Toast.makeText(CreationCompteChauffeurEtape2.this, "Création de compte réussie",
                            Toast.LENGTH_SHORT).show();
                    Intent pageAccueilAdmin = new Intent(getApplicationContext(), MonCompteAdmin.class);
                    startActivity(pageAccueilAdmin);
                    finish();

                } else {
                    Toast.makeText(CreationCompteChauffeurEtape2.this,
                            "Champs obligatoires manquants pour la création du compte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onClickRetourEtapePrecedente() {
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retourEtape1CreationCompteChauffeur = new Intent(getApplicationContext(), CreationCompteChauffeur.class);
                startActivity(retourEtape1CreationCompteChauffeur);
                finish();
            }
        });
    }
}