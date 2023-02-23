package org.doranco.parcours.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.gesttion_reserv.R;
import org.doranco.models.Administrateur;
import org.doranco.retrofit.ClientApi;
import org.doranco.retrofit.RetrofitService;

import java.util.List;

public class CreationCompteAdministrateur<AdministrateurApi> extends AppCompatActivity {
    private RetrofitService retrofitService = new RetrofitService();
    private AdministrateurApi administrateurApi;
    private Button creerCompte;
    private EditText etId, etLogin, etPassword,etEmail;
    private List<Administrateur> administrateurList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_compte_administrateur);

        //Login = findViewBy(R.id.Login);
        //administrateurApi = retrofitService.getRetrofit().create(AdministrateurApi.class);

        creerCompte.setOnClickListener(view -> {
            String id = String.valueOf(etId.getText());
            String login = String.valueOf(etLogin.getText());
            String password = String.valueOf(etPassword.getText());
            String email = String.valueOf(etEmail.getText());

            Administrateur administrateur = new Administrateur();
            /*administrateur.setId(Id);
            administrateur.setLogin(Login);
            administrateur.setPassword(Password);
            administrateur.setEmail(Email);

            AdministrateurApi.saveAdministrateur(administrateur);*/

        });

    }
}