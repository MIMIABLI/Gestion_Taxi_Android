package org.doranco.parcours.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.*;
import org.doranco.retrofit.controller.ControllerClient;
import org.doranco.retrofit.interfacesapi.ReservationApi;
import org.doranco.retrofit.RetrofitService;

import java.util.List;

public class PageReservation extends AppCompatActivity {

    private RetrofitService retrofitService = new RetrofitService();
    private ReservationApi reservationApi;
    ControllerClient controllerClient;
    Client client;

    private Button creerReservation, annulerReservation;
    private EditText resaSecteurDepart, resaDateDepart, resaLieuDepart, resaLieuArrivee, resaHeureArrivee;

    private Reservation reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_reservation);

        resaSecteurDepart = findViewById(R.id.choisirSecteur);
        resaDateDepart = findViewById(R.id.choisirDateDepart);
        resaHeureArrivee = findViewById(R.id.choisirHeureArrivee);
        resaLieuDepart = findViewById(R.id.entrerLieuDepart);
        resaLieuArrivee = findViewById(R.id.entrerDestination);
        creerReservation = findViewById(R.id.reserverBtnPageReservation);
        annulerReservation = findViewById(R.id.btnAnnulerReservation);

        reservationApi = retrofitService.getRetrofit().create(ReservationApi.class);
        reservation = (Reservation) getIntent().getSerializableExtra("reservation");

        creerReservationEtAllerVersPageChoixDuChauffeur(reservation);

    }

    private void creerReservationEtAllerVersPageChoixDuChauffeur(Reservation resa) {

        creerReservation.setOnClickListener(view -> {

            String secteur = String.valueOf(resaSecteurDepart.getText());
            String heureArrive = String.valueOf(resaHeureArrivee.getText());
            String statutTrajet = StatutTrajet.EN_ATTENTE.name();
            String dateDeDepart = String.valueOf(resaDateDepart.getText());
            String lieuDeDepart = String.valueOf(resaLieuDepart);
            String lieuDarrivee = String.valueOf(resaLieuArrivee);

            Trajet trajet = new Trajet();
            trajet.setLieuDeDepart(lieuDeDepart);
            trajet.setLieuDArrive(lieuDarrivee);
            trajet.setReservation(resa);
            trajet.setSecteur(secteur);

            //client = controllerClient.getClientByLog(ESharedDatasRefs.USER_SHARED_LOGIN.name());

            resa.setClient(client);
            resa.setStatut(StatutResa.EN_ATTENTE);

            Intent pageChoixDuChauffeur = new Intent(getApplicationContext(), ListChauffeurReservation.class);
            pageChoixDuChauffeur.putExtra("reservation", resa);
            pageChoixDuChauffeur.putExtra("trajet", trajet);
            startActivity(pageChoixDuChauffeur);
            finish();
        });
    }

}