package org.doranco.parcours.client;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;
import org.doranco.models.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListChauffeurReservation extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Chauffeur> chauffeurList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_trajet);

        Chauffeur chauffeur1 = new Chauffeur();
        chauffeur1.setNom("Riri");
        chauffeur1.setPrenom("Koko");
        chauffeur1.setTypeDeVehicules("306");
        chauffeur1.setNote(4.5);
        chauffeur1.setPrix(20.5);
        chauffeur1.setPhotos(R.drawable.avatar_homme);

        Chauffeur chauffeur2 = new Chauffeur();
        chauffeur2.setNom("Rara");
        chauffeur2.setPrenom("Koukou");
        chauffeur2.setTypeDeVehicules("Mazda 3");
        chauffeur2.setNote(4.5);
        chauffeur2.setPrix(20.5);
        chauffeur2.setPhotos(R.drawable.fille);

        Chauffeur chauffeur3 = new Chauffeur();
        chauffeur3.setNom("Ruru");
        chauffeur3.setPrenom("Kake");
        chauffeur3.setTypeDeVehicules("Clio 3");
        chauffeur3.setNote(4.5);
        chauffeur3.setPrix(20.5);
        chauffeur3.setPhotos(R.drawable.garcon);

        Chauffeur chauffeur4 = new Chauffeur();
        chauffeur4.setNom("Rere");
        chauffeur4.setPrenom("Kiko");
        chauffeur4.setTypeDeVehicules("Audi A3");
        chauffeur4.setNote(4.5);
        chauffeur4.setPrix(20.5);
        chauffeur4.setPhotos(R.drawable.avatar_homme);


        chauffeurList.add(chauffeur1);
        chauffeurList.add(chauffeur2);
        chauffeurList.add(chauffeur3);
        chauffeurList.add(chauffeur4);

        recyclerView = findViewById(R.id.listChauffeur);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), chauffeurList));
    }
}