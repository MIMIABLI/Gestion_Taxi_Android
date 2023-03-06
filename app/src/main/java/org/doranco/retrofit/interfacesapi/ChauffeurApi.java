package org.doranco.retrofit.interfacesapi;

import org.doranco.models.Chauffeur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChauffeurApi {

    @GET("/chauffeur/all")
    Call<List<Chauffeur>> getAllChauffeur();

    @POST("/chauffeur/add")
    Call<Chauffeur> saveChauffeur(@Body Chauffeur Chauffeur);

    @GET("/chauffeur/find/{id}")
    Call<List<Chauffeur>> getChauffeurById(@Path("id") Long id);

    @GET("/chauffeur/findbylogin/{login}")
    Call<Chauffeur> getChauffeurByLogin(@Path("login") String login);

    @PUT("/chauffeur/update")
    Call<List<Chauffeur>> updateChauffeur(@Body Chauffeur Chauffeur);

    @DELETE("/chauffeur/delete/{id}")
    Call<List<Chauffeur>> delete(@Path("id") long id);

    @GET("/chauffeur/allbysecteur/{secteur}")
    Call<List<Chauffeur>> getAllChauffeurBySecteur(@Path("secteur") String secteur);


}
