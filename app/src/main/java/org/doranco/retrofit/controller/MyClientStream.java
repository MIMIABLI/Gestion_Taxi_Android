package org.doranco.retrofit.controller;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.doranco.models.Client;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;

import java.util.concurrent.TimeUnit;

public class MyClientStream {

    public MyClientStream() {
    }

    /*public Observable<Client> streamObservableClient(String login) {
        RetrofitService retrofitService = new RetrofitService();
        ClientApi clientApi = retrofitService.getRetrofit().create(ClientApi.class);
        return clientApi.getClientByLogin(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }*/
}
