package org.doranco.models.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.jetbrains.annotations.NotNull;

public class MyViewHolderListeMesCoursesChauffeur extends RecyclerView.ViewHolder {

    TextView nomPrenomClient, dateResa, telClient, lieuDepart, lieuArrivee;

    public MyViewHolderListeMesCoursesChauffeur(@NonNull @NotNull View itemView) {
        super(itemView);

        nomPrenomClient = itemView.findViewById(R.id.nomPrenomClient);
        dateResa = itemView.findViewById(R.id.dateDuDepart);
        telClient = itemView.findViewById(R.id.telCLient);
        lieuDepart = itemView.findViewById(R.id.lieuDeDepart);
        lieuArrivee = itemView.findViewById(R.id.lieuDarrivee);
    }

}
