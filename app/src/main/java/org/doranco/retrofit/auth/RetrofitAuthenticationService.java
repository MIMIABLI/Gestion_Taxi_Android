package org.doranco.retrofit.auth;

import org.doranco.models.Chauffeur;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAuthenticationService {

    @POST("api/v1/auth/register")
    Call<AuthenticationResponse> registerUser(@Body RegisterRequest request);

    @POST("api/v1/auth/registerchauffeur")
    Call<AuthenticationResponse> registerChauffeur(@Body Chauffeur chauffeurRequest);

    @POST("/api/v1/auth/authenticate")
    Call<AuthenticationResponse> authenticationResponseCall(@Body AuthenticationRequest request);

}