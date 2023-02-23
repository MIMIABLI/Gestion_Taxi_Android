package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import org.doranco.parcours.client.CreationCompteClient;

public class PageConnexion extends AppCompatActivity   {
    private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_connexion);
        this.button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), CreationCompteClient.class);
                startActivity(otherActivity);
                finish();

            }
        });

























    }
}