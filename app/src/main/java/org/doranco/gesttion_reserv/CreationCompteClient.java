package org.doranco.gesttion_reserv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.models.Client;
import org.doranco.retrofit.ClientApi;
import org.doranco.retrofit.RetrofitService;

import java.io.Serializable;
import java.util.List;

public class CreationCompteClient extends AppCompatActivity {

    private Button creerCompteEtape1;
    private EditText etNom, etPrenom, etMail, etTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_compte_client_etape_1);

        etNom = findViewById(R.id.entrezNom);
        etPrenom = findViewById(R.id.entrezPrenom);
        etMail = findViewById(R.id.entrezMail);
        etTelephone = findViewById(R.id.entrezTelephone);

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

            Intent creerCompteEtape2 = new Intent(getApplicationContext(), CreationCompteClientEtape2.class);
            creerCompteEtape2.putExtra("client", client);
            startActivity(creerCompteEtape2);
            finish();

        });

    }
}