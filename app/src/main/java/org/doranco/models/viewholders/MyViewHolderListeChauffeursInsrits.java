package org.doranco.models.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

public class MyViewHolderListeChauffeursInsrits extends RecyclerView.ViewHolder {

    TextView nomPrenomChauffeur, vehicule, immatriculation, telephone, mail;

    public MyViewHolderListeChauffeursInsrits(@NonNull @NotNull View itemView) {
        super(itemView);
    }
}
