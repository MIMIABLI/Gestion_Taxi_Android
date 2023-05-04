package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.doranco.models.Client;
import org.doranco.models.UserType;
import org.doranco.parcours.admin.MonCompteAdmin;
import org.doranco.parcours.chauffeurs.MonCompteChauffeur;
import org.doranco.parcours.client.CompteClient;
import org.doranco.parcours.client.CreationCompteClient;
import org.doranco.parcours.client.ESharedDatasRefs;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.auth.AuthenticationRequest;
import org.doranco.retrofit.auth.AuthenticationResponse;
import org.doranco.retrofit.auth.ResponseCode;
import org.doranco.retrofit.auth.RetrofitAuthenticationService;
import org.doranco.retrofit.interfacesapi.ClientApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageConnexion extends AppCompatActivity {

    private Button buttonLogin, buttonSignUp;
    private EditText login, password;
    SharedPreferences sharedPreferences;
    private final RetrofitService retrofitService = new RetrofitService();
    private RetrofitAuthenticationService retrofitAuthenticationService;
    private final AuthenticationRequest authenticationRequest = new AuthenticationRequest();
    private AuthenticationResponse authenticationResponse;
    private String token;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commons_page_connexion);
        this.buttonLogin = findViewById(R.id.btnLogin);
        this.buttonSignUp = findViewById(R.id.btnSignUp);
        login = findViewById(R.id.loginPageConnexion);
        password = findViewById(R.id.passwordPageConnexion);
        retrofitAuthenticationService = retrofitService.getRetrofit().create(RetrofitAuthenticationService.class);

        connexionMonCompte();
        creationMonCompte();
        sharedPreferences = getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), MODE_PRIVATE);

    }

    public void connexionMonCompte() {
        buttonLogin.setOnClickListener(view -> {
            String loginEntree = String.valueOf(login.getText());
            String passwordEntree = String.valueOf(password.getText());

            if (!loginEntree.equals("") && !passwordEntree.equals("")) {

                authenticationRequest.setLogin(loginEntree);
                authenticationRequest.setPassword(passwordEntree);


                retrofitAuthenticationService.authenticationResponseCall(authenticationRequest).enqueue(new Callback<AuthenticationResponse>() {
                    @Override
                    public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                        if (response.code() == ResponseCode.OK.getReponseCode()) {

                            authenticationResponse = response.body();
                            token = authenticationResponse.getToken();

                            String userType = String.valueOf(authenticationResponse.getUserType());

                            sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_TOKEN.name(), token).apply();
                            sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_ID.name(), String.valueOf(response.body().getId())).apply();
                            sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_LOGIN.name(), loginEntree).apply();

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
        });
    }

    public void creationMonCompte() {
        buttonSignUp.setOnClickListener(view -> {
            Intent otherActivity = new Intent(getApplicationContext(), CreationCompteClient.class);
            startActivity(otherActivity);
            finish();
        });
    }


}