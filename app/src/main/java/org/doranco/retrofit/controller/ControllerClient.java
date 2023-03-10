package org.doranco.retrofit.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.doranco.models.Client;
import org.doranco.parcours.client.ESharedDatasRefs;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class ControllerClient {

    private ClientApi clientApi;
    RetrofitService retrofitService = new RetrofitService();
    private Client client;
    private Context context;
    private SharedPreferences sharedPreferences;
    private String clientLogin;
    private boolean clientExistance;

    public ControllerClient(Context context) {
        this.context = context;
        clientApi = retrofitService.getRetrofit().create(ClientApi.class);
    }

    public void getClientDatas(String login) throws IOException {

        Call<Client> clientCall = clientApi.getClientByLogin(login);
        clientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.isSuccessful()) {
                    client = response.body();
                    sharedPreferences = context.getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_LOGIN.name(), client.getLogin()).apply();
                    sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_MAIL.name(), client.getEmail()).apply();
                    sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_TEL.name(), client.getTelephone()).apply();
                    sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_FIRSTNAME.name(), client.getPrenom()).apply();
                    sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_NAME.name(), client.getNom()).apply();
                    sharedPreferences.edit().putString(ESharedDatasRefs.USER_SHARED_PASSWORD.name(), client.getPassword()).apply();
                    setClientExistance(true);
                    setClientLogin(client.getLogin());
                } else if (!response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(context, "Impossible de r??cup??rer les donn??es du client", Toast.LENGTH_SHORT);
                setClientExistance(false);
                setClientLogin("");
            }
        });
    }

    public String getClientLogin() {
        return clientLogin;
    }

    private void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public boolean getClientExistance() {
        return clientExistance;
    }

    private void setClientExistance(boolean clientExistance) {
        this.clientExistance = clientExistance;
    }
}
