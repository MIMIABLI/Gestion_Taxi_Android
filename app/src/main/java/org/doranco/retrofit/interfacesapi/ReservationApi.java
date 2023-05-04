package org.doranco.retrofit.interfacesapi;


import org.doranco.models.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ReservationApi {

    @Headers({"Accept: application/json"})
    @GET("/reservation/all")
    Call<List<Reservation>> getAllReservation();

    @Headers({"Accept: application/json"})
    @GET("reservation/allbychauffeur/{login}")
    Call<List<Reservation>> getAllReservationsByChauffeur(@Path("login") String login);

    @Headers({"Content-Type: application/json"})
    @GET("reservation/allbyclient/{login}")
    Call<List<Reservation>> getAllReservationsByClient(@Path("login") String login);

    @Headers({"Accept: application/json"})
    @GET("reservation/allacceptedbychauffeur/{login}")
    Call<List<Reservation>> getAllReservationsAcceptedByChauffeur(@Path("login") String login);

    @POST("/reservation/add")
    Call<Reservation> saveReservation(@Body Reservation reservation);

    @GET("/reservation/find/{id}")
   Call<List<Reservation>> getReservationById(@Path("id") Long id);

    @PUT("/reservation/update")
   Call<List<Reservation>> updateReservation(@Body Reservation reservation);

    @DELETE("/reservation/delete/{id}")
    Call<List<Reservation>> delete(@Path("id") long id);



}
