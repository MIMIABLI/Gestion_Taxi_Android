package org.doranco.models.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;
import org.doranco.models.Reservation;
import org.doranco.models.StatutResa;
import org.doranco.parcours.client.MonPaiement;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MyAdapterClientMesReservations extends RecyclerView.Adapter<MyViewHolderClientMesReservations> {

    private Context context;
    private List<Reservation> reservationList;

    public MyAdapterClientMesReservations(Context context, List<Reservation> reservationList) {
        this.context = context;
        this.reservationList = reservationList;
    }

    @NonNull
    @Override
    public MyViewHolderClientMesReservations onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderClientMesReservations(LayoutInflater.from(context).inflate(R.layout.viewholder_liste_de_resa_compte_client, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolderClientMesReservations holder, int position) {
        String nomChauffeur = String.valueOf(reservationList.get(position).getChauffeur().getNom());
        String prenomChauffeur = String.valueOf(reservationList.get(position).getChauffeur().getPrenom());
        String nomPrenomChauffeur = "Chauffeur: " + prenomChauffeur + " " + nomChauffeur;

        String statutResa = reservationList.get(position).getStatut().name();
        String dateResa = "Date départ: " + String.valueOf(reservationList.get(position).getDate());
        String departResa = "Lieu départ: " + String.valueOf(reservationList.get(position).getTrajet().getLieuDeDepart());
        String arriveeResa = "Lieu d'arrivée: " + String.valueOf(reservationList.get(position).getTrajet().getLieuDArrive());
        String heureResa;
        if(reservationList.get(position).getHeureDepart() != null) {
            heureResa = "Heure départ: " + String.valueOf(reservationList.get(position).getHeureDepart());
        } else {
            heureResa = "Heure départ: en attente";
        }

        holder.chauffeurResa.setText(nomPrenomChauffeur);
        holder.dateResa.setText(dateResa);
        holder.departResa.setText(departResa);
        holder.arriveeResa.setText(arriveeResa);
        holder.heureResa.setText(heureResa);
        holder.statutResa.setText(statutResa);

        switch (reservationList.get(position).getStatut()) {

            case EN_ATTENTE:
                holder.statutResa.setTextColor(ContextCompat.getColor(context, R.color.yellow));
                break;
            case VALIDEE:
                holder.statutResa.setTextColor(ContextCompat.getColor(context, R.color.green));
                break;
            case REFUSEE:
                holder.statutResa.setTextColor(ContextCompat.getColor(context, R.color.red));
                break;
        }


    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }
}


