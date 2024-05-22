package com.example.frontend.impl;


import com.example.frontend.interfaces.ApiService;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceImpl {
    private static ApiService apiService;
    private static final String BASE_URL = "https://do.diba.cat/api/dataset/municipis/";
    private static final String BASE_URL2 = "https://gestorapi.gencat.cat/dadesobertes/consulta/consultadades";
    private static final String BASE_URL_REGISTER = "https://192.168.9.163:8442/clientes/";

    public static ApiService getApiServiceMunicipi(String like) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (apiService == null) {
            Retrofit retrofitSingleton = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL + "camp-municipi_transliterat-like/" + like + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            apiService = retrofitSingleton.create(ApiService.class);
        }
        return apiService;
    }

    public static ApiService getApiServiceNewUser() {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (apiService == null) {
            Retrofit retrofitSingleton = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_REGISTER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            apiService = retrofitSingleton.create(ApiService.class);
        }
        return apiService;
    }

    public ApiService getClient(){
        try{
            InputStream inputStream =this.getClass().getResourceAsStream("res/raw/euroconstrucciones");
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream,"euroconstrucciones".toCharArray());

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            X509TrustManager trustManager = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

        }catch(Exception e){

        }
        return null;
    }
}
