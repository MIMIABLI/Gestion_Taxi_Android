package org.doranco.models;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageChauffeur;
    TextView nom, vehicule, note, prix;
    Button button;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageChauffeur = itemView.findViewById(R.id.imageviewtest);
        nom = itemView.findViewById(R.id.nomprenom);
        vehicule = itemView.findViewById(R.id.vehicule);
        note = itemView.findViewById(R.id.note);
        prix = itemView.findViewById(R.id.prix);
        button = itemView.findViewById(R.id.btn_reserver);
    }
}
