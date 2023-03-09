package org.doranco.models.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ChauffeurApi;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyAdapterListeChauffeursInscrits extends RecyclerView.Adapter<MyViewHolderListeChauffeursInsrits> {

    private Context context;
    private List<Chauffeur> chauffeurList;
    private String token;
    private ChauffeurApi chauffeurApi;
    private RetrofitService retrofitService;

    public MyAdapterListeChauffeursInscrits(Context context, List<Chauffeur> chauffeurList, String token) {
        this.context = context;
        this.chauffeurList = chauffeurList;
        this.token = token;
        retrofitService = new RetrofitService(token);
        chauffeurApi = retrofitService.getRetrofit().create(ChauffeurApi.class);
    }

    @NonNull
    @Override
    public MyViewHolderListeChauffeursInsrits onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderListeChauffeursInsrits(LayoutInflater.from(context).inflate(R.layout.viewholder_liste_chauffeurs_inscrits, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolderListeChauffeursInsrits holder, int position) {
            String nom = chauffeurList.get(position).getNom();
            String prenom = chauffeurList.get(position).getPrenom();
            String login = chauffeurList.get(position).getLogin();
            String telephone = chauffeurList.get(position).getTelephone();
            String mail = chauffeurList.get(position).getEmail();
            String vehicule = chauffeurList.get(position).getTypeDeVehicules();
            String immatriculation = chauffeurList.get(position).getImmatriculationDuVehicule();

            String nomPrenom = prenom + " " + nom;
            holder.nomPrenomChauffeur.setText(nomPrenom);
            holder.login.setText("Login: " + login);
            holder.telephone.setText("Téléphone: " + telephone);
            holder.mail.setText("Mail: " + mail);
            holder.vehicule.setText("Véhicule: " + vehicule);
            holder.immatriculation.setText("Immatriculation: " + immatriculation);
    }

    @Override
    public int getItemCount() {
        return chauffeurList.size();
    }
}
