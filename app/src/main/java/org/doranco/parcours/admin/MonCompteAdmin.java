package org.doranco.parcours.admin;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.doranco.gesttion_reserv.MonCompteParametres;
import org.doranco.gesttion_reserv.PageConnexion;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.viewholders.MyViewHolderListeChauffeur;
import org.doranco.utils.GetTime;
import org.doranco.utils.NowTimeTask;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class MonCompteAdmin extends AppCompatActivity {

    Button consulterListeChauffeurs, ajouterChauffeurs;
    TextView date, menuMonCompte, seDeconnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrateur_page_mon_compte);

        date = findViewById(R.id.dateCompteAdmin);
        menuMonCompte = findViewById(R.id.btnMonCompteAdmin);
        seDeconnecter = findViewById(R.id.seDeconnecterCompteAdmin);
        consulterListeChauffeurs = findViewById(R.id.btnConsulterChauffeursAdmin);
        ajouterChauffeurs = findViewById(R.id.btnAjouterChauffeur);
        date.setText(GetTime.getTodayDateAndHour(new Date()));

        ajouterUnChauffeurOnClick();
        mesParametresDeCompte();
        meDeconnecter();
    }

    private void ajouterUnChauffeurOnClick() {

        ajouterChauffeurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ajouterUnChauffeurActivity = new Intent(getApplicationContext(), CreationCompteChauffeur.class);
                startActivity(ajouterUnChauffeurActivity);
                finish();
            }
        });
    }

    private void consulterListDesChauffeursInscrits() {
        consulterListeChauffeurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listeChauffeursInscrits = new Intent(getApplicationContext(), ListeChauffeursInscrits.class);
                startActivity(listeChauffeursInscrits);
                finish();
            }
        });
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
        seDeconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pageLogin = new Intent(getApplicationContext(), PageConnexion.class);
                startActivity(pageLogin);
                finish();
            }
        });
    }
}