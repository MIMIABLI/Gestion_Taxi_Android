package org.doranco.retrofit;


import org.doranco.models.Reservation;
import org.doranco.models.Trajet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface TrajetApi {

    @GET("/trajet/all")
    Call<List<Trajet>> getAllReservation();

    @POST("/trajet/add")
    Call<Trajet> saveTrajet(@Body Trajet trajet);

    @PUT("/trajet/update")
    Call<Trajet> updateReservation(@Body Trajet trajet);

//    @GET("/find/{id}")
//    Call<Reservation> getReservationById(("id") Long id);

//    @DELETE("/delete/{id}")
//    Call<?> delete(("id") Long id);
}
