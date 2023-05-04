package org.doranco.models.viewholders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;

public class MyViewHolderListeChauffeur extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView imageChauffeur;
    TextView nom, vehicule, note, prix;
    Button button;

    public MyViewHolderListeChauffeur(@NonNull View itemView) {
        super(itemView);

        imageChauffeur = itemView.findViewById(R.id.imageviewtest);
        nom = itemView.findViewById(R.id.nomprenom);
        vehicule = itemView.findViewById(R.id.vehicule);
        note = itemView.findViewById(R.id.note);
        prix = itemView.findViewById(R.id.prix);
        button = itemView.findViewById(R.id.btn_reserver);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}
