package org.doranco.parcours.client;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;
import org.doranco.models.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChoisirChauffeur extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Chauffeur> chauffeursList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_page_trajet);

        recyclerView = findViewById(R.id.listChauffeur);

        Chauffeur chauffeur1 = new Chauffeur();
        chauffeur1.setNom("Roky");
        chauffeur1.setPrenom("Patrick");
        chauffeur1.setTypeDeVehicules("Voiture");
        chauffeur1.setNote(4.5);
        chauffeur1.setPrix(15.5);
        chauffeur1.setPhotos(R.drawable.avatar_homme);

        Chauffeur chauffeur2 = new Chauffeur();
        chauffeur2.setNom("Roro");
        chauffeur2.setPrenom("Yannick");
        chauffeur2.setTypeDeVehicules("Voiture");
        chauffeur2.setNote(4.5);
        chauffeur2.setPrix(25.00);
        chauffeur2.setPhotos(R.drawable.garcon);

        Chauffeur chauffeur3 = new Chauffeur();
        chauffeur3.setNom("Kyky");
        chauffeur3.setPrenom("Annah");
        chauffeur3.setTypeDeVehicules("Voiture");
        chauffeur3.setNote(4.5);
        chauffeur3.setPrix(18.5);
        chauffeur3.setPhotos(R.drawable.fille);

        chauffeursList.add(chauffeur1);
        chauffeursList.add(chauffeur2);
        chauffeursList.add(chauffeur3);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), chauffeursList));
    }
}