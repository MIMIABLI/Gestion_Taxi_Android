package org.doranco.retrofit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    public RetrofitService(String token) {
        initializeRetrofitWithBearerAuth(token);
    }

    private void initializeRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.48:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    private void initializeRetrofitWithBearerAuth(String token){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.48:8080")
                .client(setBearerAuth(token))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private OkHttpClient setBearerAuth(String token) {
        OkHttpClient setBearerToken = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", " Bearer " + token)
                        .build();
                return chain.proceed(request);
            }
        }).build();

        return setBearerToken;
    }

}
