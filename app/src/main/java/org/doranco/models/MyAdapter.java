package org.doranco.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.doranco.gesttion_reserv.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Chauffeur> chauffeurList;

    public MyAdapter(Context context, List<Chauffeur> chauffeurList) {
        this.context = context;
        this.chauffeurList = chauffeurList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.liste_chauffeur, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String nom = chauffeurList.get(position).getNom();
        String prenom = chauffeurList.get(position).getPrenom();

        holder.imageChauffeur.setImageResource(chauffeurList.get(position).getPhotos());
        holder.nom.setText(nom + " " + prenom);
        holder.vehicule.setText(chauffeurList.get(position).getTypeDeVehicules());
        holder.note.setText(String.valueOf(chauffeurList.get(position).getNote()) +"/5");
        holder.prix.setText(String.valueOf(chauffeurList.get(position).getPrix()) +"€");

    }

    @Override
    public int getItemCount() {

        return chauffeurList.size();
    }
}
