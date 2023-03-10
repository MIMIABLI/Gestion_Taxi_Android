package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.doranco.models.Client;
import org.doranco.models.UserType;
import org.doranco.parcours.admin.MonCompteAdmin;
import org.doranco.parcours.chauffeurs.MonCompteChauffeur;
import org.doranco.parcours.client.CompteClient;
import org.doranco.parcours.client.CreationCompteClient;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.auth.*;
import org.doranco.retrofit.controller.ControllerClient;
import org.doranco.retrofit.interfacesapi.ClientApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageConnexion extends AppCompatActivity  {

    private Button buttonLogin, buttonSignUp ;
    private EditText login, password;
    private String loginDataBaseClient, loginDataBaseChauffeur, loginDataBaseAdmin, loginDonneChauffeur, loginDonneAdmin;
    //private String loginEntree;
    private boolean isClientExists, isChauffeurExists, isAdminExists;
    private ControllerClient controllerClient;
    private RetrofitService retrofitService = new RetrofitService();
    private RetrofitAuthenticationService retrofitAuthenticationService;
    private AuthenticationRequest authenticationRequest = new AuthenticationRequest();
    private ClientApi clientApi;
    private Client client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commons_page_connexion);
        this.buttonLogin = (Button) findViewById(R.id.btnLogin);
        this.buttonSignUp = (Button) findViewById(R.id.btnSignUp);
        login = findViewById(R.id.loginPageConnexion);
        password = findViewById(R.id.passwordPageConnexion);

        clientApi = retrofitService.getRetrofit().create(ClientApi.class);
        retrofitAuthenticationService = retrofitService.getRetrofit().create(RetrofitAuthenticationService.class);

        connexionMonCompte();
        creationMonCompte();

    }

    public void connexionMonCompte(){
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view) {
                String loginEntree = String.valueOf(login.getText());
                String passwordEntree = String.valueOf(password.getText());

                if (!loginEntree.equals("") && !passwordEntree.equals("")) {

                authenticationRequest.setLogin(loginEntree);
                authenticationRequest.setPassword(passwordEntree);


                    retrofitAuthenticationService.authenticationResponseCall(authenticationRequest).enqueue(new Callback<AuthenticationResponse>() {
                        @Override
                        public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                            if (response.code() == ResponseCode.OK.getReponseCode()) {

                                AuthenticationResponse authenticationResponse = response.body();
                                String userType = String.valueOf(authenticationResponse.getUserType());

                                if (userType.equals(UserType.CLIENT.toString())) {
                                    Intent otherActivity = new Intent(getApplicationContext(), CompteClient.class);
                                    startActivity(otherActivity);
                                    finish();
                                } else if (userType.equals(UserType.CHAUFFEUR.toString())) {
                                    Intent otherActivity = new Intent(getApplicationContext(), MonCompteChauffeur.class);
                                    startActivity(otherActivity);
                                    finish();
                                } else if (userType.equals(UserType.ADMIN.toString())) {
                                    Intent otherActivity = new Intent(getApplicationContext(), MonCompteAdmin.class);
                                    startActivity(otherActivity);
                                    finish();
                                }

                            } else {
                                Toast.makeText(PageConnexion.this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                            Toast.makeText(PageConnexion.this, "Connexion au serveur impossible", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(PageConnexion.this, "Veuillez renseigner des identifiants corrects", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void creationMonCompte(){
        buttonSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), CreationCompteClient.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }



}