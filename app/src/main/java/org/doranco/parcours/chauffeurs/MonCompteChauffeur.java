package org.doranco.parcours.chauffeurs;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.doranco.gesttion_reserv.MonCompteParametres;
import org.doranco.gesttion_reserv.PageConnexion;
import org.doranco.gesttion_reserv.R;
import org.doranco.utils.GetTime;

import java.util.Date;

public class MonCompteChauffeur extends AppCompatActivity {

    TextView date, menuMonCompte, menuSeDeconnecter;
    Button btnVoirMesCourse, btnGererMesDemandesDeResa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chauffeur_page_mon_compte);

        date = findViewById(R.id.dateCompteChauffeur);
        menuMonCompte = findViewById(R.id.btnMonCompteChauffeur);
        menuSeDeconnecter = findViewById(R.id.btnDecoPageCompteChauffeur);

        btnVoirMesCourse = findViewById(R.id.btnVoirMesCourses);
        btnGererMesDemandesDeResa = findViewById(R.id.btnGererDemandeResa);

        date.setText(GetTime.getTodayDateAndHour(new Date()));

        mesParametresDeCompte();
        meDeconnecter();
        voirMesCourses();
        gererMesDemandesDeResa();

    }

    private void mesParametresDeCompte() {
        menuMonCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mesParametres = new Intent(getApplicationContext(), MonCompteParametres.class);
                startActivity(mesParametres);
                finish();
            }
        });
    }

    private void meDeconnecter() {
        menuSeDeconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pageLogin = new Intent(getApplicationContext(), PageConnexion.class);
                startActivity(pageLogin);
                finish();
            }
        });
    }

    private void voirMesCourses() {
        btnVoirMesCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pageLogin = new Intent(getApplicationContext(), VoirMaListeDeResa.class);
                startActivity(pageLogin);
                finish();
            }
        });
    }

    private void gererMesDemandesDeResa() {
        btnGererMesDemandesDeResa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pageLogin = new Intent(getApplicationContext(), MaGestionDeDemandesDeResa.class);
                startActivity(pageLogin);
                finish();
            }
        });
    }
}