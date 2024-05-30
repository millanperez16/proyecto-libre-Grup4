package com.example.frontend.impl;


import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.frontend.R;
import com.example.frontend.interfaces.ApiService;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ApiServiceImpl {
    private static ApiService apiService;
    private static final String BASE_URL = "https://do.diba.cat/api/dataset/municipis/";
    private static final String BASE_URL2 = "https://gestorapi.gencat.cat/dadesobertes/consulta/consultadades";
    private static final String BASE_URL_REGISTER = "https://localhost:8442/clientes/";
    private static final String URL_MUNICIPIS = "https://do.diba.cat/api/dataset/municipis/";
    private static final String URL_REGISTER = "https://localhost:8442/clientes/";
    private static final String URL_LOGIN = "https://localhost:8442/authenticate/";
    private static final String URL_BATHROOM = "https://localhost:8442/presupuestos/aseos/saveRefAseo/";
    private static final String URL_NEW_BUILD = "https://localhost:8442/presupuestos/obranueva/saveObra/";
    private static Context contextApp;

    public static ApiService getApiServiceMunicipi(String like) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (apiService == null) {
            Retrofit retrofitSingleton = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL_MUNICIPIS + "camp-municipi_transliterat-like/" + like + "/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            apiService = retrofitSingleton.create(ApiService.class);
        }
        return apiService;
    }

    public static ApiService getApiServiceNewUser(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        contextApp = context;

        // Asociamos el interceptor a las peticiones
        final OkHttpClient cli = getClient();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (cli != null){
            httpClient = cli.newBuilder();
            httpClient.addInterceptor(logging);
        }

        if (apiService == null) {
            Retrofit retrofitSingleton = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL_REGISTER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            apiService = retrofitSingleton.create(ApiService.class);
        }
        return apiService;
    }

    public static ApiService getApiServiceLoginUser(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        contextApp = context;

        // Asociamos el interceptor a las peticiones
        final OkHttpClient cli = getClient();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (cli != null){
            httpClient = cli.newBuilder();
            httpClient.addInterceptor(logging);
        }

        if (apiService == null) {
            Retrofit retrofitSingleton = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL_LOGIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            apiService = retrofitSingleton.create(ApiService.class);
        }
        return apiService;
    }

    public static ApiService getApiServiceNewBuildBudget(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        contextApp = context;

        // Asociamos el interceptor a las peticiones
        final OkHttpClient cli = getClient();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (cli != null){
            httpClient = cli.newBuilder();
            httpClient.addInterceptor(logging);
        }

        if (apiService == null) {
            Retrofit retrofitSingleton = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL_NEW_BUILD)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            apiService = retrofitSingleton.create(ApiService.class);
        }
        return apiService;
    }

    public static ApiService getApiServiceBathroomBudget(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        contextApp = context;

        // Asociamos el interceptor a las peticiones
        final OkHttpClient cli = getClient();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (cli != null){
            httpClient = cli.newBuilder();
            httpClient.addInterceptor(logging);
        }

        if (apiService == null) {
            Retrofit retrofitSingleton = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL_BATHROOM)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            apiService = retrofitSingleton.create(ApiService.class);
        }
        return apiService;
    }

    @Nullable
    public static OkHttpClient getClient(){
        try{
            InputStream inputStream = contextApp.getResources().openRawResource(R.raw.euroconstrucciones);

            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                Security.addProvider(new BouncyCastleProvider());
            }
            for(Provider p:Security.getProviders()){
                Log.d("Provider",p.toString());
            }
            KeyStore keyStore = KeyStore.getInstance("PKCS12",BouncyCastleProvider.PROVIDER_NAME);
            keyStore.load(inputStream,"euroconstrucciones".toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore,"euroconstrucciones".toCharArray());
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            // Obtener el X509TrustManager del TrustManagerFactory
            X509TrustManager x509TrustManager = null;
            for (TrustManager trustManager : trustManagers) {
                if (trustManager instanceof X509TrustManager) {
                    x509TrustManager = (X509TrustManager) trustManager;
                    break;
                }
            }

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(keyManagers, trustManagerFactory.getTrustManagers(), new SecureRandom());

            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                    .sslSocketFactory(socketFactory, x509TrustManager)
                    .hostnameVerifier((hostname, session) -> true);

            return clientBuilder.build();

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
