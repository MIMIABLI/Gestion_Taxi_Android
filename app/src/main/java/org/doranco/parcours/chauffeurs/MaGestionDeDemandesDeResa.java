package org.doranco.parcours.chauffeurs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Reservation;
import org.doranco.models.viewholders.MyAdapterListeResaClient;
import org.doranco.parcours.client.ESharedDatasRefs;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ReservationApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MaGestionDeDemandesDeResa extends AppCompatActivity {

    private Context context;
    private RetrofitService retrofitService;
    private ReservationApi reservationApi;
    private GetToken getToken;
    private String token;
    private RecyclerView recyclerView;
    private MyAdapterListeResaClient myAdapterListeResaClient;
    private List<Reservation> reservationsListe;
    private List<Reservation> updatedReservationsListe;
    private SharedPreferences sharedPreferences;
    private String login;
    private Button retour, valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chauffeur_page_gestion_reservation);

        retour = findViewById(R.id.btnRetourDemandeDeResa);
        valider = findViewById(R.id.btnValiderDemandeDeResa);

        context = getApplicationContext();
        getToken = new GetToken(context);
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        reservationApi = retrofitService.getRetrofit().create(ReservationApi.class);
        recyclerView = findViewById(R.id.recyclerViewMesDemandesDeCourses);
        sharedPreferences = getApplicationContext().getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), MODE_PRIVATE);
        login = sharedPreferences.getString(ESharedDatasRefs.USER_SHARED_LOGIN.name(), "");


        getMesDemandesDeResa();
        validerMesDemandesDeResa();
        retourMonCompteChauffeur();
    }

    private void getMesDemandesDeResa() {
        reservationApi.getAllReservationsByChauffeur(login).enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                reservationsListe = response.body();
                myAdapterListeResaClient = new MyAdapterListeResaClient(context, reservationsListe);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                recyclerView.setAdapter(myAdapterListeResaClient);


            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Toast.makeText(context, "Impossible d'afficher votre liste de réservations", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void retourMonCompteChauffeur() {
        retour.setOnClickListener(v -> {
            Intent monCompteChauffeur = new Intent(context, MonCompteChauffeur.class);
            startActivity(monCompteChauffeur);
            finish();
        });
    }

    private void validerMesDemandesDeResa() {
        valider.setOnClickListener(v -> {
            updatedReservationsListe = myAdapterListeResaClient.getUpdateStatusResa();

            for(int i = 0; i < updatedReservationsListe.size(); i++) {
                reservationApi.saveReservation(updatedReservationsListe.get(i)).enqueue(new Callback<Reservation>() {
                    @Override
                    public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                        myAdapterListeResaClient.notifyDataSetChanged();
                        Toast.makeText(context, "Validations et Refus réussis !", Toast.LENGTH_SHORT).show();
                        Intent monCompteChauffeur = new Intent(context, MonCompteChauffeur.class);
                        startActivity(monCompteChauffeur);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Reservation> call, Throwable t) {
                        Toast.makeText(context, "Impossible d'apporter les modifications aux réservations", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            

        });
    }
}