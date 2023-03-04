package org.doranco.retrofit.interfacesapi;

import io.reactivex.Observable;
import org.doranco.models.Client;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClientApi {

/*    @GET("/client/all")
    Observable<List<Client>> getAllClient();

    @POST("/client/add")
    Observable<Client> saveClient(@Body Client Client);

    @GET("/client/find/{id}")
    Observable<Client> getClientById(@Path("id") Long id);

    @GET("/client/findByLogin/{login}")
    Observable<Client> getClientByLogin(@Path("login") String login);

    @PUT("/client/update")
    Observable<List<Client>> updateClient(@Body Client Client);

    @DELETE("/client/delete/{id}")
    Observable<List<Client>> delete(@Path("id") long id);*/

    @GET("/client/all")
    Observable<List<Client>> getAllClient();

    @POST("/client/add")
    Call<Client> saveClient(@Body Client Client);

    @GET("/client/find/{id}")
    Call<Client> getClientById(@Path("id") Long id);

    @GET("/client/findByLogin/{login}")
    Call<Client> getClientByLogin(@Path("login") String login);

    @PUT("/client/update")
    Call<List<Client>> updateClient(@Body Client Client);

    @DELETE("/client/delete/{id}")
    Call<List<Client>> delete(@Path("id") long id);
}
