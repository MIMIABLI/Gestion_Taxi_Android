package org.doranco.parcours.client;

import android.content.SharedPreferences;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import org.apache.commons.lang3.time.DateUtils;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.*;
import org.doranco.retrofit.controller.ControllerClient;
import org.doranco.retrofit.interfacesapi.ClientApi;
import org.doranco.retrofit.interfacesapi.ReservationApi;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.TrajetApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

public class PageReservation extends AppCompatActivity {

    private RetrofitService retrofitService;
    private GetToken getToken;
    private String token;
    private TrajetApi trajetApi;
    Client client;
    private ClientApi clientApi;
    private SharedPreferences sharedPreferences;
    private String login;

    private Button creerReservation, annulerReservation;
    private EditText resaSecteurDepart, resaDateDepart, resaLieuDepart, resaLieuArrivee, resaHeureArrivee;
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

        reservation = (Reservation) getIntent().getSerializableExtra("reservation");

        getToken = new GetToken(getApplicationContext());
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        trajetApi = retrofitService.getRetrofit().create(TrajetApi.class);
        clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        sharedPreferences = getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), MODE_PRIVATE);
        login = sharedPreferences.getString(ESharedDatasRefs.USER_SHARED_LOGIN.name(), "");

        getCLient(login);
        creerReservationEtAllerVersPageChoixDuChauffeur(reservation);

    }

    private void creerReservationEtAllerVersPageChoixDuChauffeur(Reservation resa) {

        creerReservation.setOnClickListener(view -> {

            String secteur = String.valueOf(resaSecteurDepart.getText());
            StatutTrajet statutTrajet = StatutTrajet.EN_ATTENTE;
            int day = dateDepart.getDayOfMonth();
            int month = dateDepart.getMonth();
            int year = dateDepart.getYear();
            Date date = new Date(year, month, day);

            int hour = heureArrivee.getHour();
            int min = heureArrivee.getMinute();
            String heureDArrive = hour + ":" + min;

            SimpleDateFormat hdeureFormatter = new SimpleDateFormat("HH:mm");

            try {
                resa.setDate(date);
                Date heure = hdeureFormatter.parse(heureDArrive);
                resa.setHeureArrive(heure);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            String lieuDeDepart = String.valueOf(resaLieuDepart.getText());
            String lieuDarrivee = String.valueOf(resaLieuArrivee.getText());

            Trajet trajet = new Trajet();
            trajet.setLieuDeDepart(lieuDeDepart);
            trajet.setLieuDArrive(lieuDarrivee);
            trajet.setStatut(statutTrajet);
            trajet.setSecteur(secteur);

            if(isInfosOk(secteur, resa.getDate(), resa.getHeureArrive(), lieuDeDepart, lieuDarrivee)) {
                saveTrajet(trajet, resa);
            } else {
                Toast.makeText(getApplicationContext(), "Champs incorrects ou manquants", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isInfosOk(String secteur, Date date, Date heure, String depart, String arrivee) {
        boolean isOk = false;
        if (!secteur.isEmpty() && date != null && heure != null && !depart.isEmpty() && !arrivee.isEmpty()) {
            isOk = true;
        }
        return isOk;
    }

    private Client getCLient(String login) {

        clientApi.getClientByLogin(login).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                client = response.body();
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });

        return client;
    }

    private void saveTrajet(Trajet trajet, Reservation resa) {
        trajetApi.saveTrajet(trajet).enqueue(new Callback<Trajet>() {
            @Override
            public void onResponse(Call<Trajet> call, Response<Trajet> response) {
                resa.setClient(client);
                resa.setStatut(StatutResa.EN_ATTENTE);
                resa.setTrajet(trajet);

                Intent pageChoixDuChauffeur = new Intent(getApplicationContext(), ListChauffeurReservation.class);
                pageChoixDuChauffeur.putExtra("reservation", resa);
                pageChoixDuChauffeur.putExtra("trajet", trajet);
                startActivity(pageChoixDuChauffeur);
                finish();

                Toast.makeText(PageReservation.this, "Trajet saved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Trajet> call, Throwable t) {

            }
        });
    }
}