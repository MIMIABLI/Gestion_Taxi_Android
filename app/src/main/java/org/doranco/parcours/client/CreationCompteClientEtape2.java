package org.doranco.parcours.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.doranco.gesttion_reserv.CompteClient;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Client;
import org.doranco.retrofit.interfacesapi.ClientApi;
import org.doranco.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//            if(mdp == confMdp) {
//                clientSuite.setPassword(mdp);
//            } else {
//                clientSuite.setPassword("mot de passe incorrect");
//            }
            clientSuite.setPassword(mdp);

            clientSuite.setLogin(cliLog);

            System.out.println(clientSuite.getNom() + " " + clientSuite.getPrenom() + " " +
                    clientSuite.getEmail() + " " + clientSuite.getTelephone() + " " +
                    clientSuite.getLogin() + " " + clientSuite.getPassword() );


            clientApi.saveClient(clientSuite)
                    .enqueue(new Callback<Client>() {
                        @Override
                        public void onResponse(Call<Client> call, Response<Client> response) {
                            Toast.makeText(CreationCompteClientEtape2.this, R.string.addUserSuccess, Toast.LENGTH_SHORT).show();
                            Intent compteClientActivity = new Intent(getApplicationContext(), CompteClient.class);
                            startActivity(compteClientActivity);
                            finish();
                        }
                        @Override
                        public void onFailure(Call<Client> call, Throwable t) {
                            Toast.makeText(CreationCompteClientEtape2.this, R.string.addUserFailure, Toast.LENGTH_SHORT).show();
                        }
                    });


        });








    }
}