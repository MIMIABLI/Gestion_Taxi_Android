package org.doranco.retrofit.interfacesapi;


import org.doranco.models.Trajet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TrajetApi {

    @GET("/trajet/all")
    Call<List<Trajet>> getAllTrajet();

    @POST("/trajet/add")
    Call<Trajet> saveTrajet(@Body Trajet Trajet);

    @GET("/trajet/find/{id}")
    Call<List<Trajet>> getTrajetById(@Path("id") Long id);

    @PUT("/trajet/update")
    Call<List<Trajet>> updateTrajet(@Body Trajet Trajet);

    @DELETE("/trajet/delete/{id}")
    Call<List<Trajet>> delete(@Path("id") long id);
}
