package org.doranco.gesttion_reserv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.doranco.models.Reservation;
import org.doranco.retrofit.ClientApi;
import org.doranco.retrofit.ReservationApi;
import org.doranco.retrofit.RetrofitService;

import java.util.List;

public class PageReservation extends AppCompatActivity {
    private RetrofitService retrofitService = new RetrofitService();
    private ReservationApi reservationApi;
    private Button creerReservation;
    private EditText etClient, etChauffeur, etDate, etHeureDepart, etHeureArrive, etStatut, etTrajet;
    private List<Reservation>reservationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_reservation);

        //etClient FindById(R.id.Client);
        reservationApi = retrofitService.getRetrofit().create(ReservationApi.class);

        creerReservation.setOnClickListener(view -> {
            String client = String.valueOf(etClient.getText());
            String chauffeur = String.valueOf(etChauffeur.getText());
            String heureDepart = String.valueOf(etHeureDepart.getText());
            String heureArrive = String.valueOf(etHeureArrive.getText());
            String statut = String.valueOf(etStatut.getText());
            String trajet = String.valueOf(etTrajet.getText());

            Reservation reservation = new Reservation();
          //  reservation.setClient(client);
           // reservation.setChauffeur(chauffeur);
            reservation.setHeureDepart(heureDepart);
            reservation.setHeureArrive(heureArrive);
            reservation.setStatut(statut);
           // reservation.setTrajet(trajet);

            reservationApi.saveReservation(reservation);


        });
    }
}