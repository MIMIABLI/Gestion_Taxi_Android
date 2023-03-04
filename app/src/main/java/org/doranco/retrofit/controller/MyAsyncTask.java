package org.doranco.retrofit.controller;

import android.os.AsyncTask;
import android.util.Log;
import org.doranco.models.Client;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.interfacesapi.ClientApi;
import retrofit2.Call;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class MyAsyncTask extends AsyncTask<Void, Void, Client> {

        RetrofitService retrofitService = new RetrofitService();
        ControllerClient controllerClient;
        ClientApi clientApi;
        String login;
        Call<Client> clientCall;
        Client client;

        // 1 - Implement listeners methods (Callback)
        public interface Listeners {
            void onPreExecute();
            void doInBackground();
            void onPostExecute(Client client);
        }

        // 2 - Declare callback
        private final WeakReference<Listeners> callback;

        // 3 - Constructor
        public MyAsyncTask(Listeners callback, String login){
            this.callback = new WeakReference<>(callback);
            this.login = login;
            this.clientApi = retrofitService.getRetrofit().create(ClientApi.class);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.callback.get().onPreExecute(); // 4 - Call the related callback method
            Log.e("TAG", "AsyncTask is started.");
        }

        @Override
        protected void onPostExecute(Client client) {
            super.onPostExecute(client);
            this.callback.get().onPostExecute(client); // 4 - Call the related callback method
            Log.e("TAG", "AsyncTask is finished.");
            System.out.println("Is Client exists? -> " + client.getTelephone());
            System.out.println("Client login: -> " + client.getLogin());
        }

        @Override
        protected Client doInBackground(Void... voids) {
            this.callback.get().doInBackground(); // 4 - Call the related callback method
            Log.e("TAG", "AsyncTask doing some big work...");
            clientCall = clientApi.getClientByLogin(login);
            try {
              client = clientCall.execute().body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return client;
        }


    }

