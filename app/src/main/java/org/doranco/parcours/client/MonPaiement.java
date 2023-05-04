package org.doranco.parcours.client;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Client;
import org.doranco.models.Reservation;
import org.doranco.models.Trajet;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;
import org.doranco.retrofit.interfacesapi.ReservationApi;
import org.doranco.retrofit.interfacesapi.TrajetApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MonPaiement extends AppCompatActivity {

    Button valider, annuler;
    private RetrofitService retrofitService;
    private ClientApi clientApi;
    private Client client;
    private ReservationApi reservationApi;
    private Reservation reservation;
    private String token;
    private GetToken getToken;
    private SharedPreferences sharedPreferences;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_paiement);

        valider = findViewById(R.id.buttonValider);
        annuler = findViewById(R.id.buttonAnnuler);
        getToken = new GetToken(getApplicationContext());
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        clientApi = retrofitService.getRetrofit().create(ClientApi.class);
        reservationApi = retrofitService.getRetrofit().create(ReservationApi.class);

        reservation = (Reservation) getIntent().getSerializableExtra("reservation");

        sharedPreferences = getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), MODE_PRIVATE);
        login = sharedPreferences.getString(ESharedDatasRefs.USER_SHARED_LOGIN.name(), "");

        validerResa(reservation);
        annulerResa();

    }

    private void annulerResa() {
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageButtonResaEtRetourPageMonCompte("annulée !");
            }
        });
    }

    public void validerResa(Reservation reservation) {
        valider.setOnClickListener( v -> {

           clientApi.getClientByLogin(login).enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    client = response.body();
                    reservation.setClient(client);

                    reservationApi.saveReservation(reservation).enqueue(new Callback<Reservation>() {
                        @Override
                        public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                            System.out.println("Réponse: " + response.message());
                            System.out.println("Code réponse: " + response.code());
                            try {
                                System.out.println("Reservation enregistrée = " + response.body().getId());
                            } catch (NullPointerException e) {
                                System.out.println("Erreur : " + e.getMessage());
                            }

                            Toast.makeText(MonPaiement.this, "Réservation enregistrée !", Toast.LENGTH_SHORT).show();
                            messageButtonResaEtRetourPageMonCompte("Validée !");
                        }

                        @Override
                        public void onFailure(Call<Reservation> call, Throwable t) {
                            Toast.makeText(MonPaiement.this, "erreur", Toast.LENGTH_SHORT).show();
                            System.out.println("Erreur à cause de: " + t.getMessage());
                        }
                    });
                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    Toast.makeText(MonPaiement.this, "erreur 2", Toast.LENGTH_SHORT).show();
                }
            });


        });
    }
    
    private void messageButtonResaEtRetourPageMonCompte(String message) {
        Toast.makeText(this, "Réservation " + message, Toast.LENGTH_LONG).show();
        Intent retourMonCompte = new Intent(this.getApplicationContext(), CompteClient.class);
        retourMonCompte.removeExtra("reservation");
        startActivity(retourMonCompte);
        finish();

    }
}