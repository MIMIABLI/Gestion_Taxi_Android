package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import org.doranco.models.Client;
import org.doranco.parcours.client.CompteClient;
import org.doranco.parcours.client.ESharedDatasRefs;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.auth.ResponseCode;
import org.doranco.retrofit.interfacesapi.ClientApi;
import org.doranco.utils.GetToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MonCompteParametres extends AppCompatActivity {

    private EditText modifierMail, confMail, nouveauTel;
    private Button validerModif, retourMonCompte;

    private RetrofitService retrofitService;
    private ClientApi clientApi;
    private GetToken getToken;
    private String token;
    private Client client;
    private String nouveauMail;
    private String nouveauMailConf;
    private String nouveautelStrg;
    private SharedPreferences sharedPreferences;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commons_mes_parametres);

        modifierMail = findViewById(R.id.mofifierMonMail);
        confMail = findViewById(R.id.confNouveauMail);
        nouveauTel = findViewById(R.id.nouveauTel);
        validerModif = findViewById(R.id.btnValiderModifs);
        retourMonCompte = findViewById(R.id.btnRetourDeMesParams);

        nouveauMail = String.valueOf(modifierMail.getText());
        nouveauMailConf = String.valueOf(confMail.getText());
        nouveautelStrg = String.valueOf(nouveauTel.getText());

        getToken = new GetToken(getApplicationContext());
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        clientApi = retrofitService.getRetrofit().create(ClientApi.class);
        login = sharedPreferences.getString(ESharedDatasRefs.USER_SHARED_LOGIN.name(), "");


        validerModif.setOnClickListener(v -> {
            clientApi.getClientByLogin(login).enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    client = response.body();
                    Client updateClientDatas = client;
                    updateClientDatas.setEmail(nouveauMail);
                    updateClientDatas.setTelephone(nouveautelStrg);

                    clientApi.updateClient(updateClientDatas).enqueue(new Callback<List<Client>>() {
                        @Override
                        public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                            if (response.code() == ResponseCode.OK.getReponseCode()) {
                                Toast.makeText(MonCompteParametres.this, "Modifications réussies !", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MonCompteParametres.this, "Echec de la mofication, veuillez réessayer", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Client>> call, Throwable t) {
                            Toast.makeText(MonCompteParametres.this, "Echec de la modification des données", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {

                }
            });
        });

        retourMonCompte();

    }

    private void retourMonCompte() {
        retourMonCompte.setOnClickListener(v -> {
            Intent retourMonCompteAccueil = new Intent(getApplicationContext(), CompteClient.class);
            startActivity(retourMonCompteAccueil);
            finish();
        });
    }



}