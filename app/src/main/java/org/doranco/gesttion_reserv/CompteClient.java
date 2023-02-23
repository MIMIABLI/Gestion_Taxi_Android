package org.doranco.gesttion_reserv;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.doranco.models.Client;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;

public class CompteClient extends AppCompatActivity {

    TextView nomClient;
    RetrofitService retrofitService = new RetrofitService();
    ClientApi clientApi;
    Client client;
    long numId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_client);

        clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        nomClient = findViewById(R.id.nomClient);

         client = (Client) clientApi.getClientById(numId);

         nomClient.setText((client.getNom()));


    }
}