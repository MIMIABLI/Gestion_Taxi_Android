package org.doranco.models.viewholders;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.doranco.gesttion_reserv.R;
import org.jetbrains.annotations.NotNull;

public class MyViewHolderListeChauffeursInsrits extends RecyclerView.ViewHolder {

    TextView nomPrenomChauffeur, login, vehicule, immatriculation, telephone, mail, modifer, supprimer, id;
    ProgressBar progressBar;
    public MyViewHolderListeChauffeursInsrits(@NonNull @NotNull View itemView) {

        super(itemView);

        nomPrenomChauffeur = itemView.findViewById(R.id.nomPrenomChauffeurInscrit);
        login = itemView.findViewById(R.id.loginChauffeurInscrit);
        telephone = itemView.findViewById(R.id.telephoneChauffeurInscrit);
        mail = itemView.findViewById(R.id.mailChauffeurInscrit);
        vehicule = itemView.findViewById(R.id.vehiculeChauffeurInscrit);
        immatriculation = itemView.findViewById(R.id.immatriculationChauffeurInscrit);
        modifer = itemView.findViewById(R.id.modifierChauffeurInscrit);
        supprimer = itemView.findViewById(R.id.supprimerChauffeurInscrit);
        id = itemView.findViewById(R.id.chauffeurId);
    }
}
