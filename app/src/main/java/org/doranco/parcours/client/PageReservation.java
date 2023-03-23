package org.doranco.parcours.client;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Reservation;
import org.doranco.models.StatutResa;
import org.doranco.models.StatutTrajet;
import org.doranco.models.Trajet;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.TrajetApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PageReservation extends AppCompatActivity {

    private RetrofitService retrofitService;
    private GetToken getToken;
    private String token;
    private TrajetApi trajetApi;


    private Button creerReservation, annulerReservation;
    private EditText resaSecteurDepart, resaLieuDepart, resaLieuArrivee;
    private DatePicker dateDepart;
    private TimePicker heureArrivee;

    private Reservation reservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_reservation);

        resaSecteurDepart = findViewById(R.id.choisirSecteur);
        dateDepart = findViewById(R.id.datePicker);
        heureArrivee = findViewById(R.id.timePicker);
        resaLieuDepart = findViewById(R.id.entrerLieuDepart);
        resaLieuArrivee = findViewById(R.id.entrerDestination);
        creerReservation = findViewById(R.id.reserverBtnPageReservation);
        annulerReservation = findViewById(R.id.btnAnnulerReservation);

        dateDepart.setMinDate(new Date().getTime());

        getToken = new GetToken(getApplicationContext());
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        trajetApi = retrofitService.getRetrofit().create(TrajetApi.class);


        creerReservationEtAllerVersPageChoixDuChauffeur();
        retourAccueilMonCompte();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reservation = new Reservation();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void creerReservationEtAllerVersPageChoixDuChauffeur() {

        creerReservation.setOnClickListener(view -> {
            Reservation resa = new Reservation();
            String secteur = String.valueOf(resaSecteurDepart.getText());
            StatutTrajet statutTrajet = StatutTrajet.EN_ATTENTE;
            String lieuDeDepart = String.valueOf(resaLieuDepart.getText());
            String lieuDarrivee = String.valueOf(resaLieuArrivee.getText());

            int day = dateDepart.getDayOfMonth();
            int month = dateDepart.getMonth();
            int year = dateDepart.getYear();
            String date = day +"-"+(month + 1) +"-"+year;
            int hour = heureArrivee.getHour();
            int min = heureArrivee.getMinute();
            String heureDArrive = hour + ":" + min;

            try {
                resa.setDate(date);
                resa.setHeureArrive(heureDArrive);
                System.out.println("heure arrivée format date: " + resa.getHeureArrive());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            Trajet trajet = new Trajet();
            trajet.setLieuDeDepart(lieuDeDepart);
            trajet.setLieuDArrive(lieuDarrivee);
            trajet.setStatut(statutTrajet);
            trajet.setSecteur(secteur);
            System.out.println("arrivée: " + lieuDarrivee);

            resa.setStatut(StatutResa.EN_ATTENTE);

            if (isInfosOk(secteur, resa.getDate(), resa.getHeureArrive(), trajet.getLieuDeDepart(), trajet.getLieuDArrive())) {
                saveTrajet(trajet, resa);
                resa.setTrajet(trajet);
                Intent pageChoixDuChauffeur = new Intent(getApplicationContext(), ListChauffeurReservation.class);
                pageChoixDuChauffeur.putExtra("reservation", resa);
                pageChoixDuChauffeur.putExtra("trajet", trajet);
                startActivity(pageChoixDuChauffeur);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Champs incorrects ou manquants", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isInfosOk(String secteur, String date, String heure, String depart, String arrivee) {
        boolean isOk = false;
        if (!secteur.isEmpty() && !date.isEmpty() && !heure.isEmpty() && !depart.isEmpty() && !arrivee.isEmpty()) {
            isOk = true;
        }
        return isOk;
    }

    private void saveTrajet(Trajet trajet, Reservation resa) {
        trajetApi.saveTrajet(trajet).enqueue(new Callback<Trajet>() {
            @Override
            public void onResponse(Call<Trajet> call, Response<Trajet> response) {
                Toast.makeText(PageReservation.this, "Trajet saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Trajet> call, Throwable t) {

            }
        });
    }

    private void retourAccueilMonCompte() {
        annulerReservation.setOnClickListener(v -> {
            Intent retourMonCompte = new Intent(getApplicationContext(), CompteClient.class);
            startActivity(retourMonCompte);
            finish();
        });
    }

}