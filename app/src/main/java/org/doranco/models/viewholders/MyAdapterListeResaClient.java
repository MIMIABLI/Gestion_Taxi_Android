package org.doranco.models.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Reservation;

import java.util.List;

public class MyAdapterListeResaClient extends RecyclerView.Adapter<MyViewHolderListeResaClient> {

    Context context;
    List<Reservation> resaList;

    public MyAdapterListeResaClient(Context context, List<Reservation> resaList) {
        this.context = context;
        this.resaList = resaList;
    }

    @NonNull
    @Override
    public MyViewHolderListeResaClient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderListeResaClient(LayoutInflater.from(context).inflate(R.layout.viewholder_liste_resa_client, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListeResaClient holder, int position) {
        String nom = resaList.get(position).getClient().getPrenom();
        String prenom = resaList.get(position).getClient().getNom();
        String dateResa = String.valueOf(resaList.get(position).getDate());
        String heureDepart = String.valueOf(resaList.get(position).getHeureDepart());
        String lieuDepart = resaList.get(position).getTrajet().getLieuDeDepart();
        String lieuArrivee = resaList.get(position).getTrajet().getLieuDArrive();

        holder.nomPrenomClient.setText(nom + " " + prenom);
        holder.dateResa.setText(dateResa);
        holder.heureResa.setText(heureDepart);
        holder.lieuDepart.setText(lieuDepart);
        holder.lieuArrivee.setText(lieuArrivee);

    }

    @Override
    public int getItemCount() {

        return resaList.size();
    }

}
