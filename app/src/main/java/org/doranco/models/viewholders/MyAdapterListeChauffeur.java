package org.doranco.models.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterListeChauffeur extends RecyclerView.Adapter<MyViewHolderListeChauffeur> {

    private Context context;
    private List<Chauffeur> chauffeurList;

    public MyAdapterListeChauffeur(Context context, List<Chauffeur> chauffeurList) {
        this.context = context;
        this.chauffeurList = chauffeurList;
    }

    @NonNull
    @Override
    public MyViewHolderListeChauffeur onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderListeChauffeur(LayoutInflater.from(context).inflate(R.layout.viewholder_liste_chauffeur, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListeChauffeur holder, int position) {
        String nom = chauffeurList.get(position).getNom();
        String prenom = chauffeurList.get(position).getPrenom();
        String nomPrenom = prenom + " " + nom;


        holder.imageChauffeur.setImageResource(chauffeurList.get(position).getPhotos());
        holder.nom.setText(nomPrenom);
        holder.vehicule.setText(chauffeurList.get(position).getTypeDeVehicules());
        holder.note.setText(String.valueOf(chauffeurList.get(position).getNote()) +"/5");
        holder.prix.setText(String.valueOf(chauffeurList.get(position).getPrix()) +"€");
        holder.prix.setText(String.valueOf(chauffeurList.get(position).getPrix()) +"€");
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Course Réservée avec " + nom, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return chauffeurList.size();
    }
}
