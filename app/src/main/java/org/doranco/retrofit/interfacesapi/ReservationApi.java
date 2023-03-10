package org.doranco.retrofit.interfacesapi;


import org.doranco.models.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReservationApi {

    @GET("/reservation/all")
    Call<List<Reservation>> getAllReservation();

    @POST("/reservation/add")
    Call<Reservation> saveReservation(@Body Reservation reservation);

    @GET("/reservation/find/{id}")
   Call<List<Reservation>> getReservationById(@Path("id") Long id);

    @PUT("/reservation/update")
   Call<List<Reservation>> updateReservation(@Body Reservation reservation);

    @DELETE("/reservation/delete/{id}")
    Call<List<Reservation>> delete(@Path("id") long id);



}
