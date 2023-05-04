package org.doranco.retrofit.interfacesapi;

import okhttp3.Response;
import okhttp3.ResponseBody;
import org.doranco.models.Chauffeur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ChauffeurApi {

    @GET("/chauffeur/all")
    Call<List<Chauffeur>> getAllChauffeur();

    @POST("/chauffeur/add")
    Call<Chauffeur> saveChauffeur(@Body Chauffeur Chauffeur);

    @GET("/chauffeur/find/{id}")
    Call<List<Chauffeur>> getChauffeurById(@Path("id") Long id);

    @Headers({"Content-Type: application/json"})
    @GET("/chauffeur/findbylogin/{login}")
    Call<Chauffeur> getChauffeurByLogin(@Path("login") String login);

    @PUT("/chauffeur/update")
    Call<List<Chauffeur>> updateChauffeur(@Body Chauffeur Chauffeur);

    @DELETE("/chauffeur/delete/{id}")
    Call<ResponseBody> delete(@Path("id") long id);

    @GET("/chauffeur/allbysecteur/{secteur}")
    Call<List<Chauffeur>> getAllChauffeurBySecteur(@Path("secteur") String secteur);

    @GET("/chauffeur/allbysecteur/{secteur}")
    Call<List<Chauffeur>> getAllChauffeurBySecteur(@Path("secteur") String secteur);


}
