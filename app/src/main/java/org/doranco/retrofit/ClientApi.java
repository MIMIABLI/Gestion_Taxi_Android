package org.doranco.retrofit;

import org.doranco.models.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientApi {

    @GET("/client/all")
    Call<List<Client>> getAllClients();

    @POST("/client/add")
    Call<Client> saveClient(@Body Client client);
}
