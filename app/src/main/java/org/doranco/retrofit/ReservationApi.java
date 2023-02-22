package org.doranco.retrofit;


import org.doranco.models.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ReservationApi {

    @GET("/all")
    Call<List<Reservation>> getAllReservation();

    @POST("/reservation/add")
    Call<Reservation> saveReservation(@Body Reservation reservation);

    //@GET("/find/{id}")
   // Call List<Reservation> getReservationById(@PathVariable("id") Long id);

    @PUT("/update")
   Call<List<Reservation>> updateReservation(@Body Reservation reservation);

    @DELETE("/delete/{id}")
    Call<List<Reservation>> delete();



}
