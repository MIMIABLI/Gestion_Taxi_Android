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
        trajet = (Trajet) getIntent().getSerializableExtra("trajet");
        
        validerResa();
        annulerResa();

    }
    
    public void validerResa() {
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationApi.saveReservation(reservation);
                trajetApi.saveTrajet(trajet);
                messageButtonResaEtRetourPageMonCompte("validée !");


            }
        });

    }
    
    public void annulerResa() {
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageButtonResaEtRetourPageMonCompte("annulée !");
            }
        });

    }
    
    public void messageButtonResaEtRetourPageMonCompte(String message) {
        Toast.makeText(this, "Réservation " + message, Toast.LENGTH_LONG).show();
        Intent retourMonCompte = new Intent(this.getApplicationContext(), CompteClient.class);
        startActivity(retourMonCompte);
        finish();

    }
}