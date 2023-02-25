package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.doranco.models.Client;
import org.doranco.parcours.client.CompteClient;
import org.doranco.parcours.client.CreationCompteClient;
import org.doranco.retrofit.controller.ControllerClient;

import java.io.IOException;

public class PageConnexion extends AppCompatActivity   {

    private Button buttonLogin, buttonSignUp ;
    private EditText login, password;
    private ControllerClient controllerClient;
    private String clientLogin;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commons_page_connexion);
        this.buttonLogin = (Button) findViewById(R.id.btnLogin);
        this.buttonSignUp = (Button) findViewById(R.id.btnSignUp);
        this.login = findViewById(R.id.loginPageConnexion);
        this.password = findViewById(R.id.passwordPageConnexion);

         clientLogin = String.valueOf(login.getText());

        controllerClient = new ControllerClient(getApplicationContext());

        connexionMonCompte();
        creationMonCompte();

    }

    public void connexionMonCompte(){
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view) {
                controllerClient.getClientDatas("test");
                System.out.println(clientLogin);
                Intent otherActivity = new Intent(getApplicationContext(), CompteClient.class);
                startActivity(otherActivity);
                finish();
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