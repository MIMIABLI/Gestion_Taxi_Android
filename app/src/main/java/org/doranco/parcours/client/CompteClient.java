package org.doranco.parcours.client;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.doranco.gesttion_reserv.MonCompteParametres;
import org.doranco.gesttion_reserv.PageConnexion;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Client;
import org.doranco.models.Reservation;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;
import org.doranco.utils.GetTime;

import java.util.Date;

public class CompteClient extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    TextView date, menuMonCompte, menuSeDeconnecter;
    Button btnReserver, btnConsulterMesReservation;
    RetrofitService retrofitService = new RetrofitService();
    ClientApi clientApi;
    Client client = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_mon_compte);

        clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        btnReserver = findViewById(R.id.btnReserverCompteClient);
        btnConsulterMesReservation = findViewById(R.id.btnConsulterReservationCompteClient);
        date = findViewById(R.id.dateCompteClient);
        menuMonCompte = findViewById(R.id.btnMonCompteClient);
        menuSeDeconnecter = findViewById(R.id.seDeconnecterCompteClient);

        date.setText(GetTime.getTodayDateAndHour(new Date()));

        client.setNom(getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), MODE_PRIVATE)
                .getString(ESharedDatasRefs.USER_SHARED_NAME.name(), ""));

        faireUneReservation();
        consulterMesReservations();
        mesParametresDeCompte();
        meDeconnecter();
        System.out.println(client.getLogin());


    }

    private void faireUneReservation() {
        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Reservation resa = new Reservation();

                Intent faireUneReservation = new Intent(getApplicationContext(), PageReservation.class);
                faireUneReservation.putExtra("reservation", resa);
                startActivity(faireUneReservation);
                finish();
            }
        });
    }

    private void consulterMesReservations() {
        btnConsulterMesReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consulterMesReservations = new Intent(getApplicationContext(), MaListeDeReservations.class);
                startActivity(consulterMesReservations);
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
        menuSeDeconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pageLogin = new Intent(getApplicationContext(), PageConnexion.class);
                startActivity(pageLogin);
                finish();
            }
        });
    }
}