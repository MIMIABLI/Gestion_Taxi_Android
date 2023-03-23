package org.doranco.models.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.jetbrains.annotations.NotNull;

public class MyViewHolderListeResaClient extends RecyclerView.ViewHolder {

    TextView nomPrenomClient, dateResa, heureResa, lieuDepart, lieuArrivee, validerBtn, refuserBtn;

    public MyViewHolderListeResaClient(@NonNull @NotNull View itemView) {
        super(itemView);

        nomPrenomClient = itemView.findViewById(R.id.nomPrenomClient);
        dateResa = itemView.findViewById(R.id.dateDuDepart);
        heureResa = itemView.findViewById(R.id.heureDArrivee);
        lieuDepart = itemView.findViewById(R.id.lieuDeDepart);
        lieuArrivee = itemView.findViewById(R.id.lieuDarrivee);
        validerBtn = itemView.findViewById(R.id.switchValidation);
        refuserBtn = itemView.findViewById(R.id.switchRefus);
    }
}
