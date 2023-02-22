package org.doranco.retrofit;

import org.doranco.models.Chauffeur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ChauffeurApi {

    @GET("chauffeur/all");
    Call<List<Chauffeur>> getAllChauffeur();

    @POST("chauffeur/add");
    Call<List<Chauffeur>>saveChauffeur (@Body Chauffeur chauffeur);


}
