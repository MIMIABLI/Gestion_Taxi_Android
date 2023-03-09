package org.doranco.parcours.chauffeurs;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Reservation;
import org.doranco.models.viewholders.MyAdapterListeResaClient;
import org.doranco.models.viewholders.MyViewHolderListeResaClient;
import org.doranco.parcours.client.ESharedDatasRefs;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ReservationApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class VoirMaListeDeResa extends AppCompatActivity {

    private Context context;
    private RetrofitService retrofitService;
    private ReservationApi reservationApi;
    private GetToken getToken;
    private String token;
    private RecyclerView recyclerView;
    private MyAdapterListeResaClient myAdapterListeResaClient;
    private List<Reservation> reservationsListe;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chauffeur_voir_ma_liste_de_resa);
        context = getApplicationContext();
        getToken = new GetToken(context);
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        reservationApi = retrofitService.getRetrofit().create(ReservationApi.class);
        recyclerView = findViewById(R.id.recyclerViewMesCourses);
        sharedPreferences = getApplicationContext().getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), MODE_PRIVATE);
        Long id = Long.valueOf(sharedPreferences.getLong(ESharedDatasRefs.USER_SHARED_LOGIN.name(), 0));

        reservationApi.getAllResaervationsByChauffeur(id).enqueue(new Callback<List<Reservation>>() {
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
}