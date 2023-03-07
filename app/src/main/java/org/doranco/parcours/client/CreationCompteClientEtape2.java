package org.doranco.parcours.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.doranco.gesttion_reserv.R;
import org.doranco.models.Client;
import org.doranco.retrofit.auth.AuthenticationResponse;
import org.doranco.retrofit.auth.RegisterRequest;
import org.doranco.retrofit.auth.RetrofitAuthenticationService;
import org.doranco.retrofit.auth.UserType;
import org.doranco.retrofit.interfacesapi.ClientApi;
import org.doranco.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreationCompteClientEtape2 extends AppCompatActivity {

    RetrofitService retrofitService = new RetrofitService();
    RetrofitAuthenticationService authenticationService;
    EditText login, password, confirmationPassword;
    Button creerMonCompte;

    Client clientSuite = new Client();
    RegisterRequest registerClient = new RegisterRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_creation_compte_etape_2);

        login = findViewById(R.id.entrezLogin);
        password = findViewById(R.id.entrezMotDePasse);
        confirmationPassword = findViewById(R.id.entrezMotDePasseConfirmation);
        creerMonCompte = findViewById(R.id.boutonCreationCompte);

        authenticationService = retrofitService.getRetrofit().create(RetrofitAuthenticationService.class);

        clientSuite = (Client) getIntent().getSerializableExtra("client");

        creerMonCompteEtapeFinale();

    }

    private void creerMonCompteEtapeFinale() {
        creerMonCompte.setOnClickListener(view -> {

            String cliLog = String.valueOf(login.getText());
            String mdp = String.valueOf(password.getText());
            String confMdp = String.valueOf(confirmationPassword.getText());

            registerClient.setNom(clientSuite.getNom());
            registerClient.setPrenom(clientSuite.getPrenom());
            registerClient.setLogin(cliLog);

            registerClient.setTelephone(clientSuite.getTelephone());
            registerClient.setEmail(clientSuite.getEmail());
            registerClient.setUserType(UserType.CLIENT);

            if(mdp.equals(confMdp)) {
                registerClient.setPassword(mdp);
            } else {
                Toast.makeText(this, "mot de passe incorrect.", Toast.LENGTH_SHORT).show();
            }

            saveClientInDatabase(registerClient);

        });
    }

    private void saveClientInDatabase(RegisterRequest client) {
        authenticationService.registerUser(client)
                .enqueue(new Callback<AuthenticationResponse>() {
                    @Override
                    public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                        Toast.makeText(CreationCompteClientEtape2.this, R.string.addUserSuccess, Toast.LENGTH_SHORT).show();
                        Intent compteClientActivity = new Intent(getApplicationContext(), CompteClient.class);
                        startActivity(compteClientActivity);
                        finish();
                    }
                    @Override
                    public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
            Toast.makeText(CreationCompteClientEtape2.this, R.string.addUserFailure, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}