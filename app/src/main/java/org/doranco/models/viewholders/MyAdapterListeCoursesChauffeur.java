package org.doranco.models.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Reservation;

import java.util.List;

public class MyAdapterListeCoursesChauffeur extends RecyclerView.Adapter<MyViewHolderListeMesCoursesChauffeur> {

    Context context;
    List<Reservation> resaList;

    public MyAdapterListeCoursesChauffeur(Context context, List<Reservation> resaList) {
        this.context = context;
        this.resaList = resaList;
    }

    @NonNull
    @Override
    public MyViewHolderListeMesCoursesChauffeur onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderListeMesCoursesChauffeur(LayoutInflater.from(context).inflate(R.layout.viewholder_liste_courses_acceptees, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListeMesCoursesChauffeur holder, int position) {
        String nom = resaList.get(position).getClient().getPrenom();
        String prenom = resaList.get(position).getClient().getNom();
        String dateResa = String.valueOf(resaList.get(position).getDate());
        String telClientStr = String.valueOf(resaList.get(position).getClient().getTelephone());
        String lieuDepart = resaList.get(position).getTrajet().getLieuDeDepart();
        String lieuArrivee = resaList.get(position).getTrajet().getLieuDArrive();

        holder.nomPrenomClient.setText(nom + " " + prenom);
        holder.dateResa.setText(dateResa);
        holder.telClient.setText(telClientStr);
        holder.lieuDepart.setText("Depart: " + lieuDepart);
        holder.lieuArrivee.setText("Arrivée: " + lieuArrivee);

    }

    @Override
    public int getItemCount() {

        return resaList.size();
    }
}
