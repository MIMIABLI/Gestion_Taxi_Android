package org.doranco.parcours.client;

import android.util.Patterns;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.gesttion_reserv.R;
import org.doranco.models.Client;

public class CreationCompteClient extends AppCompatActivity {

    private Button creerCompteEtape1;
    private EditText etNom, etPrenom, etMail, etTelephone;
    private boolean isInfoValide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_creation_compte_etape_1);

        etNom = findViewById(R.id.entrezNom);
        etPrenom = findViewById(R.id.entrezPrenom);
        etMail = findViewById(R.id.entrezMail);
        etTelephone = findViewById(R.id.entrezTelephone);
        creerCompteEtape1 = findViewById(R.id.btnContinuerCreationCompte);

        continuerCreationCompteClient();

    }

    private void continuerCreationCompteClient() {
        creerCompteEtape1.setOnClickListener(view -> {
            String nom = String.valueOf(etNom.getText());
            String prenom = String.valueOf(etPrenom.getText());
            String mail = String.valueOf(etMail.getText());
            String telephone = String.valueOf(etTelephone.getText());

            Client client = new Client();
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setEmail(mail);
            client.setTelephone(telephone);

            infosRemplies(client.getNom(), client.getPrenom(), client.getEmail(), client.getTelephone());


            if (isInfoValide) {
                Intent creerCompteEtape2 = new Intent(getApplicationContext(), CreationCompteClientEtape2.class);
                creerCompteEtape2.putExtra("client", client);
                startActivity(creerCompteEtape2);
                finish();
            }


        });
    }

    private boolean infosRemplies(String nom, String prenom, String mail, String tel) {
        if (nom.isEmpty() || prenom.isEmpty() || mail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches() || tel.isEmpty() || !Patterns.PHONE.matcher(tel).matches()) {
            isInfoValide = false;
            Toast.makeText(this, "Informations manquantes ou incorrectes.", Toast.LENGTH_SHORT).show();
        } else {
            isInfoValide = true;
        }
        return isInfoValide;
    }
}