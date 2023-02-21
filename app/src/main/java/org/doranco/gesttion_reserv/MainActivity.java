package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity   {
    private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_connexion);
        this.button = findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), Reservation.class);
                startActivity(otherActivity);
                finish();

            }
        });

























    }
}