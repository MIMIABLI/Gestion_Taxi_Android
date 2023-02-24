package org.doranco.gesttion_reserv;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.doranco.models.Client;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompteClient extends AppCompatActivity {

    TextView nomClient, menuMonCompte, menuSeDéconnecter;
    Button btnReserver, btnConsulterMesReservation;
    RetrofitService retrofitService = new RetrofitService();
    ClientApi clientApi;
    Client client = new Client();
    long numId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_client);

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


    }


    private void getClientDatas() {
        Call<Client> clientCall = clientApi.getClientById(numId);
        clientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                System.out.println(client = response.body());
                nomClient.setText("Bonjour " + (client.getNom()));
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