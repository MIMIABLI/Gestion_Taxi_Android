package org.doranco.retrofit.interfacesapi;

import org.doranco.models.Administrateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdminApi {

    @GET("/admin/all")
    Call<List<Administrateur>> getAllAdministrateur();

    @POST("/admin/add")
    Call<Administrateur> saveAdministrateur(@Body Administrateur Administrateur);

    @GET("/admin/find/{id}")
    Call<List<Administrateur>> getAdministrateurById(@Path("id") Long id);

    @PUT("/admin/update")
    Call<List<Administrateur>> updateAdministrateur(@Body Administrateur Administrateur);

    @DELETE("/admin/delete/{id}")
    Call<List<Administrateur>> delete(@Path("id") long id);
}
