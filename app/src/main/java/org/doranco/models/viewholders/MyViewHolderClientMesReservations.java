package org.doranco.models.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;

public class MyViewHolderClientMesReservations extends RecyclerView.ViewHolder {


    TextView dateResa, heureResa, departResa, arriveeResa, chauffeurResa, statutResa;

    public MyViewHolderClientMesReservations(@NonNull View itemView) {
            super(itemView);

            dateResa = itemView.findViewById(R.id.dateResaClient);
            heureResa = itemView.findViewById(R.id.heureResaClient);
            departResa = itemView.findViewById(R.id.lieuDeDepartResaClient);
            arriveeResa = itemView.findViewById(R.id.lieuDarriveeResaClient);
            chauffeurResa = itemView.findViewById(R.id.nomPrenomChauffeurResaClient);
            statutResa = itemView.findViewById(R.id.statutResaClient);

        }

}
