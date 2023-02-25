package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import org.doranco.parcours.client.CreationCompteClient;

public class PageConnexion extends AppCompatActivity   {
    private Button buttonLogin, buttonSignUp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commons_page_connexion);
        this.buttonLogin = (Button) findViewById(R.id.btnLogin);
        this.buttonSignUp = (Button) findViewById(R.id.btnSignUp);

        connexionMonCompte();
        creationMonCompte();

    }

    public void connexionMonCompte(){
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view) {
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