package org.doranco.retrofit.auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAuthenticationService {

    @POST("/api/v1/auth/authenticate")
    Call<AuthenticationResponse> authenticationResponseCall(@Body AuthenticationRequest request);

}