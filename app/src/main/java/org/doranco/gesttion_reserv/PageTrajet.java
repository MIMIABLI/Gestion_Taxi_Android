package org.doranco.gesttion_reserv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.doranco.models.Trajet;
import org.doranco.retrofit.ClientApi;
import org.doranco.retrofit.RetrofitService;

import java.util.List;

public class PageTrajet extends AppCompatActivity {
    private RetrofitService retrofitService = new RetrofitService();
    private TrajetApi trajetApi;
    private Button creerCompte;
    private EditText etLieuDepart, etLieuArrivee, etDureeTrajet, etPrix, etStatut;
    private List<Reservation> reservationList.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_trajet);

        //etLieuDepart = FindById(R.Id.LieuDepart;
        trajetApi = retrofitService.getRetrofit().create(TrajetApi.class);

        creerCompte.setOnClickListener(view ->{
            String lieuDepat = String.valueOf(etLieuDepart.getText());
            String lieuArrive = String.valueOf(etLieuArrivee.getText());
            String durreTrajet = String.valueOf(etDureeTrajet.getText());
            String prix = String.valueOf(etPrix.getText());
            String statut= String.valueOf(etStatut.getText());

            Trajet trajet= new Trajet();
            trajet.setLieuDepart(lieuDepat);
            trajet.setLieuArrive(lieuArrive);
            trajet.setDureeTrajet(durreTrajet);
            trajet.set.Prix(prix);
            trajet.setStatut(statut);

            TrajetApi.savetrajet(Trajet);

            @GetMapping("/all")
            public ResponseEntity<List<Reservation>> getAllReservation(){
                List<Reservation> reservationList=reservationService.findAll();
                return new ResponseEntity<>(reservationList, HttpStatus.OK);
            }

            @GetMapping("/find/{id}")
            public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) throws Exception {
                Reservation reservation=reservationService.findReservationById(id);
                return new ResponseEntity<>(reservation, HttpStatus.OK);
            }
            @PutMapping("/update")
            public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation){
                Reservation updateReservation= reservationService.update(reservation);
                return new ResponseEntity<>(updateReservation,HttpStatus.OK);
            }
            @DeleteMapping("/delete/{id}")
            public ResponseEntity<?> delete(@PathVariable("id") Long id){
                reservationService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }


        });

    }
}