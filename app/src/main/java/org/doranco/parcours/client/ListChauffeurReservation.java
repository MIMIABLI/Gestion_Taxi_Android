package org.doranco.parcours.client;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ListChauffeurReservation extends AppCompatActivity {

    RecyclerView recyclerView;
    RetrofitService retrofitService;
    ChauffeurApi chauffeurApi;
    List<Chauffeur> chauffeurList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    Reservation reservation;
    String token;
    Trajet trajet;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_trajet);
        sharedPreferences = getApplicationContext().getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), MODE_PRIVATE);
        sharedPreferences.getString("token", "");


        token = (String) sharedPreferences.getAll().get("token");
        retrofitService = new RetrofitService(token);
        context = getApplicationContext();
        reservation = (Reservation) getIntent().getSerializableExtra("reservation");
        trajet = (Trajet) getIntent().getSerializableExtra("trajet");

        recyclerView = findViewById(R.id.listChauffeur);
        chauffeurApi = retrofitService.getRetrofit().create(ChauffeurApi.class);
        chauffeurApi.getAllChauffeurBySecteur("69").enqueue(new Callback<List<Chauffeur>>() {
            @Override
            public void onResponse(Call<List<Chauffeur>> call, Response<List<Chauffeur>> response) {
                chauffeurList = response.body();

                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(new MyAdapterListeChauffeur(getApplicationContext(), chauffeurList));
            }

            @Override
            public void onFailure(Call<List<Chauffeur>> call, Throwable t) {

            }
        });


    }
}