package org.doranco.gesttion_reserv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.models.Client;
import org.doranco.retrofit.ClientApi;
import org.doranco.retrofit.RetrofitService;

public class CreationCompteClientEtape2 extends AppCompatActivity {

    RetrofitService retrofitService = new RetrofitService();
    ClientApi clientApi;
    EditText login, password, confirmationPassword;
    Button creerMonCompte;

    Client clientSuite = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_compte_client_etape_2);

        login = findViewById(R.id.entrezLogin);
        password = findViewById(R.id.entrezMotDePasse);
        confirmationPassword = findViewById(R.id.entrezMotDePasseConfirmation);
        creerMonCompte = findViewById(R.id.boutonCreationCompte);

        clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        clientSuite = (Client) getIntent().getSerializableExtra("client");

        creerMonCompte.setOnClickListener(view -> {

            String cliLog = String.valueOf(login.getText());
            String mdp = String.valueOf(password.getText());
            String confMdp = String.valueOf(confirmationPassword.getText());

            if(mdp == confMdp) {
                clientSuite.setPassword(mdp);
            } else {
                clientSuite.setPassword("mot de passe incorrect");
            }

            clientSuite.setLogin(cliLog);

            System.out.println(clientSuite.getNom() + " " + clientSuite.getPrenom() + " " +
                    clientSuite.getEmail() + " " + clientSuite.getTelephone() + " " +
                    clientSuite.getLogin() + " suite " + clientSuite.getPassword() );


            //clientApi.saveClient(clientSuite);

        });








    }
}