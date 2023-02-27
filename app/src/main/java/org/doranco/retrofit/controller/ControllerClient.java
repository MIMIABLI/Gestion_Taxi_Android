package org.doranco.retrofit.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.doranco.models.Client;
import org.doranco.parcours.client.ESharedDatasRefs;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControllerClient {

    private ClientApi clientApi;
    RetrofitService retrofitService = new RetrofitService();
    private Client client;
    private Context context;
    private SharedPreferences sharedPreferences;

    public ControllerClient(Context context) {
        this.context = context;
        clientApi = retrofitService.getRetrofit().create(ClientApi.class);
    }

    public void getClientDatas(String login) {

        Call<Client> clientCall = clientApi.getClientByLogin(login);
        clientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {

                client = response.body();
                sharedPreferences = context.getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), Context.MODE_PRIVATE);
                sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_LOGIN.name(), client.getLogin()).apply();
                sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_MAIL.name(), client.getEmail()).apply();
                sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_TEL.name(), client.getTelephone()).apply();
                sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_FIRSTNAME.name(), client.getPrenom()).apply();
                sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_NAME.name(), client.getNom()).apply();
                sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_PASSWORD.name(), client.getPassword()).apply();

                System.out.println(client.getLogin());
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(context, "Impossible de récupérer les données du client", Toast.LENGTH_SHORT);
            }
        });
    }

    public Client getClientByLog(String login) {
        return (Client) clientApi.getClientByLogin(login);
    }

}
