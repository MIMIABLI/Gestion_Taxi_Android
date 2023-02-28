package org.doranco.parcours.admin;

import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.doranco.gesttion_reserv.R;
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
    TextView date, monCompte, seDeconnecter;
    NowTimeTask nowTimeTask = new NowTimeTask();
    String dateDuJourParSeconde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrateur_page_mon_compte);

        date = findViewById(R.id.dateCompteAdmin);
        monCompte = findViewById(R.id.btnMonCompteAdmin);
        seDeconnecter = findViewById(R.id.seDeconnecterCompteAdmin);
        consulterListeChauffeurs = findViewById(R.id.btnConsulterChauffeursAdmin);
        ajouterChauffeurs = findViewById(R.id.btnAjouterChauffeur);

         dateDuJourParSeconde =  nowTimeTask.getNowTime();
         date.setText(dateDuJourParSeconde);


    }
}