package org.doranco.models.viewholders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import org.doranco.gesttion_reserv.R;
import org.doranco.models.Chauffeur;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ChauffeurApi;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MyAdapterListeChauffeursInscrits extends RecyclerView.Adapter<MyViewHolderListeChauffeursInsrits> {

    private Context context;
    private List<Chauffeur> chauffeurList;
    private String token;
    private ChauffeurApi chauffeurApi;
    private RetrofitService retrofitService;
    private ProgressBar progressBar;

    public MyAdapterListeChauffeursInscrits(Context context, List<Chauffeur> chauffeurList, String token, ProgressBar progressBar) {
        this.context = context;
        this.chauffeurList = chauffeurList;
        this.token = token;
        this.progressBar = progressBar;
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
        String chauffeurId = String.valueOf(chauffeurList.indexOf(chauffeurList.get(position)) + 1);

        String nomPrenom = prenom + " " + nom;
        holder.nomPrenomChauffeur.setText(nomPrenom);
        holder.login.setText("Login: " + login);
        holder.telephone.setText("Téléphone: " + telephone);
        holder.mail.setText("Mail: " + mail);
        holder.vehicule.setText("Véhicule: " + vehicule);
        holder.immatriculation.setText("Immatriculation: " + immatriculation);
        holder.id.setText("Id: " + chauffeurId);

        progressBar.setVisibility(View.GONE);

        holder.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                chauffeurApi.getChauffeurByLogin(login).enqueue(new Callback<Chauffeur>() {
                    @Override
                    public void onResponse(Call<Chauffeur> call, Response<Chauffeur> response) {

                        Chauffeur chauffeurToDelete = response.body();
                        if (chauffeurToDelete != null) {
                            chauffeurApi.delete(chauffeurToDelete.getId()).enqueue(new Callback<ResponseBody>() {

                                @Override
                                public void onResponse(Call<okhttp3.ResponseBody> call, Response<okhttp3.ResponseBody> response) {
                                    if (response.code() == 200) {
                                        Toast.makeText(context, "Chauffeur supprimé avec succès ! " +
                                                "\nAppuyez sur retour pour rafraîchir la liste", Toast.LENGTH_SHORT).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onFailure(Call<okhttp3.ResponseBody> call, Throwable t) {

                                }
                            });
                        }
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

}
