package org.doranco.parcours.admin;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;

public class CreationCompteChauffeur extends AppCompatActivity {
    private Button creerCompteChauffeurValidationEtape1;
    private EditText nomChauffeur, prenomChauffeur, mailChauffeur, telephoneChauffeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chauffeur_creation_compte);

        creerCompteChauffeurValidationEtape1 = findViewById(R.id.continuerCreationCompteChauffeur);
        nomChauffeur = findViewById(R.id.entrezNomChauffeur);
        prenomChauffeur = findViewById(R.id.entrezPrenomChauffeur);
        mailChauffeur = findViewById(R.id.entrezMailChauffeur);
        telephoneChauffeur = findViewById(R.id.entrezTelephoneChauffeur);

        onclickbuttonsuivant();
    }

    private void onclickbuttonsuivant() {
        creerCompteChauffeurValidationEtape1.setOnClickListener(view -> {
            String nom = String.valueOf(nomChauffeur.getText());
            String prenom = String.valueOf(prenomChauffeur.getText());
            String mail = String.valueOf(mailChauffeur.getText());
            String tel = String.valueOf(telephoneChauffeur.getText());

            Chauffeur chauffeur = new Chauffeur();
            chauffeur.setNom(nom);
            chauffeur.setPrenom(prenom);
            chauffeur.setEmail(mail);
            chauffeur.setTelephone(tel);

            Intent creerCompteChauffeurEtape2 = new Intent(getApplicationContext(), CreationCompteChauffeurEtape2.class);
            creerCompteChauffeurEtape2.putExtra("chauffeur", chauffeur);
            startActivity(creerCompteChauffeurEtape2);
            finish();

        });
    }
}