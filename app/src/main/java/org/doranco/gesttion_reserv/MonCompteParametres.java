package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private TextView monMail, monTel;
    private Button validerModif, retourMonCompte;

    private RetrofitService retrofitService;
    private ClientApi clientApi;
    private GetToken getToken;
    private String token;
    private Client client;
    private String nouveauMail;
    private String nouveauMailConf;
    private String nouveautelStrg;
    private boolean isNewMailValide;
    private boolean isTelValide;
    private SharedPreferences sharedPreferences;
    private String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commons_mes_parametres);

        modifierMail = (EditText) findViewById(R.id.mofifierMonMail);
        confMail = (EditText) findViewById(R.id.confNouveauMail);
        nouveauTel = (EditText) findViewById(R.id.nouveauTel);
        monMail = findViewById(R.id.mailEnregiste);
        monTel = findViewById(R.id.telEnregiste);
        validerModif = findViewById(R.id.btnValiderModifs);
        retourMonCompte = findViewById(R.id.btnRetourDeMesParams);



        getToken = new GetToken(getApplicationContext());
        token = getToken.getToken();
        retrofitService = new RetrofitService(token);
        clientApi = retrofitService.getRetrofit().create(ClientApi.class);
        sharedPreferences = getSharedPreferences(ESharedDatasRefs.USER_SHARED_DATAS.name(), MODE_PRIVATE);
        login = sharedPreferences.getString(ESharedDatasRefs.USER_SHARED_LOGIN.name(), "");

        clientApi.getClientByLogin(login).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {

                monTel.setText(response.body().getTelephone());
                monMail.setText(response.body().getEmail());

            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {

            }
        });

        validerModif.setOnClickListener(v -> {
            nouveauMail = modifierMail.getText().toString();
            nouveauMailConf = confMail.getText().toString();
            nouveautelStrg = nouveauTel.getText().toString();
            validerModifInfos();
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

    private void validerModifInfos() {


                validateEmail();
                if(isNewMailValide == true) {
                    changerInfos();
                } else {
                    Toast.makeText(getApplicationContext(), "Les mails ne correspondent pas ou ne sont pas dans un format valide", Toast.LENGTH_SHORT).show();
                }
    }

    private void changerInfos() {
                    clientApi.getClientByLogin(login).enqueue(new Callback<Client>() {
                        @Override
                        public void onResponse(Call<Client> call, Response<Client> response) {
                            client = response.body();
                            Client updateClientDatas = client;
                            updateClientDatas.setEmail(nouveauMail);

                            if (nouveautelStrg.length() == 10 && Patterns.PHONE.matcher(nouveautelStrg).matches()) {
                                updateClientDatas.setTelephone(nouveautelStrg);
                                isTelValide = true;
                            }else if (nouveautelStrg.isEmpty()) {
                                updateClientDatas.setTelephone(monTel.getText().toString());
                                isTelValide = true;
                            } else {
                                Toast.makeText(MonCompteParametres.this, "Numéro de téléphone incorrect", Toast.LENGTH_SHORT).show();
                                isTelValide = false;
                            }

                            if (isNewMailValide && isTelValide) {
                            clientApi.updateClient(updateClientDatas).enqueue(new Callback<Client>() {
                                @Override
                                public void onResponse(Call<Client> call, Response<Client> response) {
                                    if (response.code() == ResponseCode.OK.getReponseCode()) {
                                        Toast.makeText(MonCompteParametres.this, "Modifications réussies !", Toast.LENGTH_SHORT).show();
                                        Intent retourMonCompteClient = new Intent(getApplicationContext(), CompteClient.class);
                                        startActivity(retourMonCompteClient);
                                        finish();
                                    } else {
                                        Toast.makeText(MonCompteParametres.this, "Echec de la mofication, veuillez réessayer", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Client> call, Throwable t) {
                                    Toast.makeText(MonCompteParametres.this, "Echec de la modification des données", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        }

                        @Override
                        public void onFailure(Call<Client> call, Throwable t) {

                        }
                    });

    }

    private boolean validateEmail(){
        boolean newMail = !nouveauMail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(nouveauMail).matches();
        boolean confNewMail = !nouveauMailConf.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(nouveauMailConf).matches();
        if(newMail && confNewMail) {
            isNewMailValide = true;
        } else {
            isNewMailValide = false;
        }
        return isNewMailValide;
    }

}