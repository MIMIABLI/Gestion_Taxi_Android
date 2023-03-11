package org.doranco.models.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;
import org.doranco.models.Reservation;
import org.doranco.models.Trajet;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ChauffeurApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MyAdapterListeChauffeur extends RecyclerView.Adapter<MyViewHolderListeChauffeur> {

    private Context context;
    private List<Chauffeur> chauffeurList;
    IChangeActivity changeActivity;
    RetrofitService retrofitService;
    ChauffeurApi chauffeurApi;

    public MyAdapterListeChauffeur(Context context, List<Chauffeur> chauffeurList, String token, IChangeActivity changeActivity) {
        this.context = context;
        this.chauffeurList = chauffeurList;
        this.changeActivity = changeActivity;
        this.retrofitService = new RetrofitService(token);
    }

    @NonNull
    @Override
    public MyViewHolderListeChauffeur onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderListeChauffeur(LayoutInflater.from(context).inflate(R.layout.viewholder_liste_chauffeur, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListeChauffeur holder, int position) {
        String nom = chauffeurList.get(position).getNom();
        String prenom = chauffeurList.get(position).getPrenom();
        String nomPrenom = prenom + " " + nom;

        holder.imageChauffeur.setImageResource(chauffeurList.get(position).getPhotos());
        holder.nom.setText(nomPrenom);
        holder.vehicule.setText(chauffeurList.get(position).getTypeDeVehicules());
        holder.note.setText(String.valueOf(chauffeurList.get(position).getNote()) +"/5");
        holder.prix.setText(String.valueOf(chauffeurList.get(position).getPrix()) +"€");

        String chauffeurLogin = chauffeurList.get(position).getLogin();
        chauffeurApi = retrofitService.getRetrofit().create(ChauffeurApi.class);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chauffeurApi.getChauffeurByLogin(chauffeurLogin).enqueue(new Callback<Chauffeur>() {
                    @Override
                    public void onResponse(Call<Chauffeur> call, Response<Chauffeur> response) {
                        Chauffeur chauffeur = response.body();
                        Toast.makeText(context, "Course Réservée avec " + nom, Toast.LENGTH_SHORT).show();
                        changeActivity.changeActivity(chauffeur);
                    }

                    @Override
                    public void onFailure(Call<Chauffeur> call, Throwable t) {
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return chauffeurList.size();
    }

    public interface IChangeActivity {
        void changeActivity(Chauffeur chauffeur);
    }



}
