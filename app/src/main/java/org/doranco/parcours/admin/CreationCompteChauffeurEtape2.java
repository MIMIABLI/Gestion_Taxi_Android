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
import org.doranco.retrofit.auth.AuthenticationResponse;
import org.doranco.retrofit.auth.RetrofitAuthenticationService;
import org.doranco.retrofit.interfacesapi.ChauffeurApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreationCompteChauffeurEtape2 extends AppCompatActivity {

    private RetrofitService retrofitService;
    private RetrofitAuthenticationService authenticationService;
    private GetToken getToken;
    private String token;
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

        getToken = new GetToken(getApplicationContext());
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);

        authenticationService = retrofitService.getRetrofit().create(RetrofitAuthenticationService.class);
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
                chauffeur.setLogin(chauffeur.getPrenom());

                if (chauffeur != null) {
                    authenticationService.registerChauffeur(chauffeur).enqueue(new Callback<AuthenticationResponse>() {
                        @Override
                        public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                                Toast.makeText(CreationCompteChauffeurEtape2.this, "Création de compte réussie",
                                        Toast.LENGTH_SHORT).show();
                                Intent pageAccueilAdmin = new Intent(getApplicationContext(), MonCompteAdmin.class);
                                startActivity(pageAccueilAdmin);
                                finish();
                            }

                            @Override
                            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                                Toast.makeText(CreationCompteChauffeurEtape2.this,
                                        "Erreur lors de l'enregistrement du chauffeur.", Toast.LENGTH_SHORT).show();
                            }
                    });

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