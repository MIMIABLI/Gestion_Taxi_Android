package org.doranco.parcours.client;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.doranco.gesttion_reserv.MaListeDeReservations;
import org.doranco.gesttion_reserv.MonCompteParametres;
import org.doranco.gesttion_reserv.PageConnexion;
import org.doranco.gesttion_reserv.PageReservation;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Client;
import org.doranco.models.Reservation;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompteClient extends AppCompatActivity {

    private final String SHARED_PREF_USER = "SHARED_PREF_USER";
    private final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";
    private SharedPreferences sharedPreferences;

    TextView nomClient, menuMonCompte, menuSeDéconnecter;
    Button btnReserver, btnConsulterMesReservation;
    RetrofitService retrofitService = new RetrofitService();
    ClientApi clientApi;
    Client client = new Client();
    long numId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_mon_compte);

        clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        btnReserver = findViewById(R.id.btnReserverCompteClient);
        btnConsulterMesReservation = findViewById(R.id.btnConsulterReservationCompteClient);
        nomClient = findViewById(R.id.nomClient);
        menuMonCompte = findViewById(R.id.btnMonCompteClient);
        menuSeDéconnecter = findViewById(R.id.seDeconnecterCompteClient);

        getClientDatas();
        faireUneReservation();
        consulterMesReservations();
        mesParametresDeCompte();
        meDeconnecter();
        System.out.println(client.getLogin());


    }


    private void getClientDatas() {
        Call<Client> clientCall = clientApi.getClientByLogin("test");
        clientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                System.out.println(client = response.body());
                nomClient.setText("Bonjour " + (client.getNom()));
                sharedPreferences = getSharedPreferences(SHARED_PREF_USER, MODE_PRIVATE);
                sharedPreferences.edit()
                        .putString(SHARED_PREF_USER_INFO_NAME, client.getLogin())
                        .apply();

            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Impossible de récupérer les données du client", Toast.LENGTH_SHORT);
            }
        });
    }

    private void faireUneReservation() {
        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reservation resa = new Reservation();
                String loginCLient = getSharedPreferences(SHARED_PREF_USER, MODE_PRIVATE)
                        .getString(SHARED_PREF_USER_INFO_NAME, null);

                if (loginCLient != null) {
                    clientApi.getClientByLogin(loginCLient);
//                    resa.setClient();
                }

                Intent faireUneReservation = new Intent(getApplicationContext(), PageReservation.class);
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
        menuSeDéconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pageLogin = new Intent(getApplicationContext(), PageConnexion.class);
                startActivity(pageLogin);
                finish();
            }
        });
    }
}