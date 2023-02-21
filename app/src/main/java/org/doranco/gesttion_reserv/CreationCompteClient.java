package org.doranco.gesttion_reserv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.models.Client;
import org.doranco.retrofit.ClientApi;
import org.doranco.retrofit.RetrofitService;

import java.util.List;

public class CreationCompteClient extends AppCompatActivity {

    private RetrofitService retrofitService = new RetrofitService();
    private ClientApi clientApi;
    private Button creerCompte;
    private EditText etNom, etPrenom, etMotDePasse, etLogin, etMail, etTelephone;
    private List<Reservation> reservationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_compte_client);

        //etNom = findViewById(R.id.nom);
        clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        creerCompte.setOnClickListener(view -> {
            String nom = String.valueOf(etNom.getText());
            String prenom = String.valueOf(etPrenom.getText());
            String motDePasse = String.valueOf(etMotDePasse.getText());
            String login = String.valueOf(etLogin.getText());
            String mail = String.valueOf(etMail.getText());
            String telephone = String.valueOf(etTelephone.getText());

            Client client = new Client();
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setPassword(motDePasse);
            client.setLogin(login);
            client.setEmail(mail);
            client.setTelephone(telephone);

            clientApi.saveClient(client);

        });

    }
}