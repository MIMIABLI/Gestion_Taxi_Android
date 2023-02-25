package org.doranco.gesttion_reserv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.models.Trajet;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.TrajetApi;

import java.util.List;

public class PageTrajet extends AppCompatActivity {
    private RetrofitService retrofitService = new RetrofitService();
    private TrajetApi trajetApi;
    private Button creerTrajet;
    private EditText etLieuDepart, etLieuArrivee, etDureeTrajet, etPrix, etStatut;
    private List<Trajet> trajetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_trajet);

        //etLieuDepart = FindById(R.Id.LieuDepart;
        trajetApi = retrofitService.getRetrofit().create(TrajetApi.class);

        creerTrajet.setOnClickListener(view ->{
            String lieuDepat = String.valueOf(etLieuDepart.getText());
            String lieuArrive = String.valueOf(etLieuArrivee.getText());
            String durreTrajet = String.valueOf(etDureeTrajet.getText());
            String prix = String.valueOf(etPrix.getText());

            Trajet trajet= new Trajet();
            trajet.setLieuDeDepart(lieuDepat);
            trajet.setLieuDArrive(lieuArrive);
            trajet.setDureeTrajet(durreTrajet);
            trajet.setPrix(Double.valueOf(prix));
            //trajet.setStatut(statut);

            trajetApi.saveTrajet(trajet);
        });

    }
}