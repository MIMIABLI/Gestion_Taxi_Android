package org.doranco.parcours.client;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;
import org.doranco.models.Reservation;
import org.doranco.models.Trajet;
import org.doranco.models.viewholders.MyAdapterListeChauffeur;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ChauffeurApi;
import org.doranco.retrofit.interfacesapi.ReservationApi;
import org.doranco.retrofit.interfacesapi.TrajetApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ListChauffeurReservation extends AppCompatActivity implements MyAdapterListeChauffeur.IChangeActivity{

    RecyclerView recyclerView;
    RetrofitService retrofitService;
    ChauffeurApi chauffeurApi;
    List<Chauffeur> chauffeurList = new ArrayList<>();
    MyAdapterListeChauffeur myAdapterListeChauffeur;
    GetToken getToken;
    Reservation reservation;
    String token;
    Trajet trajet;
    Context context;
    MyAdapterListeChauffeur.IChangeActivity changeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_trajet);
        getToken = new GetToken(getApplicationContext());
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        context = getApplicationContext();
        reservation = (Reservation) getIntent().getSerializableExtra("reservation");
        trajet = (Trajet) getIntent().getSerializableExtra("trajet");

        recyclerView = findViewById(R.id.listChauffeur);

        changeActivity = this;

        chauffeurApi = retrofitService.getRetrofit().create(ChauffeurApi.class);


        chauffeurApi.getAllChauffeurBySecteur(trajet.getSecteur()).enqueue(new Callback<List<Chauffeur>>() {
            @Override
            public void onResponse(Call<List<Chauffeur>> call, Response<List<Chauffeur>> response) {
                chauffeurList = response.body();
                if (chauffeurList.isEmpty()) {
                    Toast.makeText(context, "Pas de chauffeurs enregistrés dans ce département", Toast.LENGTH_LONG).show();
                } else {
                    myAdapterListeChauffeur = new MyAdapterListeChauffeur(getApplicationContext(), chauffeurList, token, changeActivity, reservation);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(myAdapterListeChauffeur);
                }
            }

            @Override
            public void onFailure(Call<List<Chauffeur>> call, Throwable t) {

            }
        });
    }

    @Override
    public void changeActivity(Chauffeur chauffeur, Reservation resa) {
        resa.setChauffeur(chauffeur);

        Intent paiement = new Intent(getApplicationContext(), MonPaiement.class);
        paiement.putExtra("reservation", resa);
        startActivity(paiement);
        finish();
    }
}