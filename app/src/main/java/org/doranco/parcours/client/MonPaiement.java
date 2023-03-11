package org.doranco.parcours.client;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Reservation;
import org.doranco.models.Trajet;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ReservationApi;
import org.doranco.retrofit.interfacesapi.TrajetApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonPaiement extends AppCompatActivity {

    Button valider, annuler;
    private RetrofitService retrofitService;
    private ReservationApi reservationApi;
    private TrajetApi trajetApi;
    private Reservation reservation;
    private Trajet trajet;
    private String token;
    private GetToken getToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_paiement);

        valider = findViewById(R.id.buttonValider);
        annuler = findViewById(R.id.buttonAnnuler);
        getToken = new GetToken(getApplicationContext());
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        trajetApi = retrofitService.getRetrofit().create(TrajetApi.class);
        reservationApi = retrofitService.getRetrofit().create(ReservationApi.class);

        reservation = (Reservation) getIntent().getSerializableExtra("reservation");
//        trajet = (Trajet) getIntent().getSerializableExtra("trajet");
        System.out.println("CHauffeur:" + reservation.getChauffeur().getNom());

        valider.setOnClickListener( v -> {
            reservationApi.saveReservation(reservation).enqueue(new Callback<Reservation>() {
                @Override
                public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                    messageButtonResaEtRetourPageMonCompte("Validé !");
                }

                @Override
                public void onFailure(Call<Reservation> call, Throwable t) {

                }
            });
                });

        annulerResa();

    }

 /*   private void validerAction() {
        try {
            reservation.setTrajet(trajet);
            trajet.setReservation(reservation);
            reservationApi.saveReservation(reservation).enqueue(new Callback<Reservation>() {
                @Override
                public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                    Toast.makeText(MonPaiement.this, "Résa validée", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Reservation> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        messageButtonResaEtRetourPageMonCompte("Validé ! ");
    }
    
    private void validerResa() {
        reservationApi.saveReservation(reservation).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                Toast.makeText(MonPaiement.this, "Résa validée", Toast.LENGTH_SHORT).show();
                System.out.println("Réservation enregistrée");
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {

            }
        });

    }

    private void validerTrajet() {
        trajetApi.saveTrajet(trajet).enqueue(new Callback<Trajet>() {
            @Override
            public void onResponse(Call<Trajet> call, Response<Trajet> response) {
                System.out.println("Trajet enregistré.");
            }

            @Override
            public void onFailure(Call<Trajet> call, Throwable t) {

            }
        });
    }
    */
    private void annulerResa() {
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageButtonResaEtRetourPageMonCompte("annulée !");
            }
        });

    }


    
    private void messageButtonResaEtRetourPageMonCompte(String message) {
        Toast.makeText(this, "Réservation " + message, Toast.LENGTH_LONG).show();
        Intent retourMonCompte = new Intent(this.getApplicationContext(), CompteClient.class);
        startActivity(retourMonCompte);
        finish();

    }
}