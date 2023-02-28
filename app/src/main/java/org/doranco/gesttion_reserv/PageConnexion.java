package org.doranco.gesttion_reserv;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.doranco.models.Client;
import org.doranco.parcours.client.CompteClient;
import org.doranco.parcours.client.CreationCompteClient;
import org.doranco.retrofit.RetrofitService;
import org.doranco.retrofit.controller.ControllerClient;
import org.doranco.retrofit.controller.MyAsyncTask;
import org.doranco.retrofit.interfacesapi.ClientApi;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class PageConnexion extends AppCompatActivity implements MyAsyncTask.Listeners  {

    private Button buttonLogin, buttonSignUp ;
    private EditText login, password;
    private String loginDataBaseClient, loginDataBaseChauffeur, loginDataBaseAdmin, loginDonneChauffeur, loginDonneAdmin;
    //private String loginEntree;
    private boolean isClientExists, isChauffeurExists, isAdminExists;
    private ControllerClient controllerClient;
    private RetrofitService retrofitService = new RetrofitService();
    private ClientApi clientApi;
    private Client client;
    private MyAsyncTask myAsyncTask;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commons_page_connexion);
        this.buttonLogin = (Button) findViewById(R.id.btnLogin);
        this.buttonSignUp = (Button) findViewById(R.id.btnSignUp);
        login = findViewById(R.id.loginPageConnexion);
        this.password = findViewById(R.id.passwordPageConnexion);

        clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        controllerClient = new ControllerClient(getApplicationContext());

        connexionMonCompte();
        creationMonCompte();

    }

    public void connexionMonCompte(){
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view) {

                String loginEntree = String.valueOf(login.getText());

                /*try {
                    controllerClient.getClientDatas(loginEntree);

                    System.out.println(loginDataBaseClient);


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }*/

                    startAsyncTask(loginEntree);


//                if (loginEntree != null &&  isClientExists) {

                    Intent otherActivity = new Intent(getApplicationContext(), CompteClient.class);
                    startActivity(otherActivity);
                    finish();
//                } else {
//                   Toast.makeText(PageConnexion.this, "Problème de connexion", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    public void creationMonCompte(){
        buttonSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(), CreationCompteClient.class);
                startActivity(otherActivity);
                finish();
            }
        });
    }


        private void startAsyncTask(String login) {
            new MyAsyncTask(this, login, controllerClient).execute();
        }



        @Override
        public void onPreExecute() {
            //this.updateUIBeforeTask();
        }

        @Override
        public void doInBackground() {

        }

        @Override
        public void onPostExecute(Call<Client> success) {
            //this.updateUIAfterTask(success);

        }

        /*public void updateUIBeforeTask(){
            this.progressBar.setVisibility(View.VISIBLE);
        }

        public void updateUIAfterTask(Call<Client> success){
            this.progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Task is finally finished at : "+success+" !", Toast.LENGTH_SHORT).show();
        }*/
}