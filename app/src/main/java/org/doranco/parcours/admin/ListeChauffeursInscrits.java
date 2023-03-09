package org.doranco.parcours.admin;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;
import org.doranco.models.viewholders.MyAdapterListeChauffeursInscrits;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ChauffeurApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ListeChauffeursInscrits extends AppCompatActivity {

    private Context context;
    private List<Chauffeur> chauffeurList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapterListeChauffeursInscrits myAdapterListeChauffeursInscrits;
    private RetrofitService retrofitService;
    private ChauffeurApi chauffeurApi;
    private GetToken getToken;
    private String token;
    private Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrateur_liste_chauffeurs_inscrits);

        retour = findViewById(R.id.btnRetourListeChauffeurInscrits);
        recyclerView = findViewById(R.id.listeChauffeursInscrits);
        context = getApplicationContext();
        getToken = new GetToken(getApplicationContext());
        token = getToken.getToken();

        retrofitService = new RetrofitService(token);
        chauffeurApi = retrofitService.getRetrofit().create(ChauffeurApi.class);

        chauffeurApi.getAllChauffeur().enqueue(new Callback<List<Chauffeur>>() {
            @Override
            public void onResponse(Call<List<Chauffeur>> call, Response<List<Chauffeur>> response) {
                chauffeurList = response.body();
                myAdapterListeChauffeursInscrits = new MyAdapterListeChauffeursInscrits(getApplicationContext(), chauffeurList, token);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(myAdapterListeChauffeursInscrits);
            }

            @Override
            public void onFailure(Call<List<Chauffeur>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Impossible d'afficher la liste des chauffeurs inscrits.", Toast.LENGTH_SHORT).show();
            }
        });

        retourMonCompteAdmin();

    }


    public void retourMonCompteAdmin() {
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monCompteAdminAccueil = new Intent(getApplicationContext(), MonCompteAdmin.class);
                startActivity(monCompteAdminAccueil);
                finish();
            }
        });
    }
}