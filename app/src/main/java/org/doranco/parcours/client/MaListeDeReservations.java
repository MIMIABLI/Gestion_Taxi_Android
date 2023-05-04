package org.doranco.parcours.client;

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
import org.doranco.models.Client;
import org.doranco.models.Reservation;
import org.doranco.models.viewholders.MyAdapterClientMesReservations;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;
import org.doranco.retrofit.interfacesapi.ReservationApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MaListeDeReservations extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private MyAdapterClientMesReservations adapterClientMesReservations;
    private RetrofitService retrofitService;
    private ClientApi clientApi;
    private ReservationApi reservationApi;
    private Client client;
    private List<Reservation> reservationList = new ArrayList<>();
    private GetToken getToken;
    private String token;
    private Button retour;
    private SharedPreferences sharedPreferences;
    private String loginCLient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.client_page_ma_liste_de_resa);
        retour = findViewById(R.id.retourMonCompteClient);
        context = getApplicationContext();
        getToken = new GetToken(context);
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        clientApi = retrofitService.getRetrofit().create(ClientApi.class);
        reservationApi = retrofitService.getRetrofit().create(ReservationApi.class);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewMesReservations);
        sharedPreferences = getApplicationContext().getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), MODE_PRIVATE);
        loginCLient = sharedPreferences.getString(ESharedDatasRefs.USER_SHARED_LOGIN.name(), "");

        reservationApi.getAllReservationsByClient(loginCLient).enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                reservationList = response.body();
                if (reservationList != null) {
                    System.out.println("onResponse -> OK");
                    adapterClientMesReservations = new MyAdapterClientMesReservations(context, reservationList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    recyclerView.setAdapter(adapterClientMesReservations);
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                System.out.println("KO");
                System.out.println("cause: " + t.getMessage());
            }
        });
           /* clientApi.getClientByLogin(loginCLient).enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    client = response.body();
                    if(client != null) {
                        reservationList = client.getListReservation();
                        System.out.println("onResponse -> OK");
                        System.out.println(reservationList.get(0).getChauffeur());
                        adapterClientMesReservations = new MyAdapterClientMesReservations(context, reservationList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(adapterClientMesReservations);
                    } else {
                        Toast.makeText(context, "Aucune liste de réservation trouvé, problème d'identification du login client", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    System.out.println("onFailure -> KO: " + t.getMessage());
                    System.out.println("Cause by " + t.getLocalizedMessage());
                }
            });*/

                retourMonCompteClient();
    }

    private void retourMonCompteClient() {
        retour.setOnClickListener(v -> {
            Intent retourMonCompteClient = new Intent(context, CompteClient.class);
            startActivity(retourMonCompteClient);
            finish();
        });
    }
}