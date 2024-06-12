package com.example.frontend.impl;


import android.content.Context;

import androidx.annotation.Nullable;

import com.example.frontend.R;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.Budget;
import com.example.frontend.models.BudgetDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.security.KeyStore;
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
import retrofit2.converter.scalars.ScalarsConverterFactory;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ApiServiceImpl {
    private static ApiService apiService;
    private static final String URL_MUNICIPIS = "https://do.diba.cat/api/dataset/municipis/";
    private static final String URL_WEBSERVICE = "https://10.0.2.2:8442/";
    private static Retrofit retrofit;
    private static Context contextApp;
    public static OkHttpClient cli;
    private static OkHttpClient.Builder clientWithToken = null;

    public static ApiService getApiServiceMunicipi(String like) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofitMunicipis = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_MUNICIPIS + "camp-municipi_transliterat-like/" + like + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofitMunicipis.create(ApiService.class);
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
        if (cli != null) {
            httpClient = cli.newBuilder();
            httpClient.addInterceptor(logging);
        }
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_WEBSERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    public static ApiService getApiServiceLoginUser(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        contextApp = context;
        // Asociamos el interceptor a las peticiones
        cli = getClient();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (cli != null) {
            httpClient = cli.newBuilder();
            httpClient.addInterceptor(logging);
        }
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_WEBSERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    public static ApiService getApiServiceNewBuildBudget(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        contextApp = context;
        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = getClientWithToken();
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_WEBSERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    public static ApiService getApiServiceBathroomBudget(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        contextApp = context;

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = getClientWithToken();
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_WEBSERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    public static ApiService getApiServiceKitchenBudget(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        contextApp = context;

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = getClientWithToken();
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_WEBSERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    public static ApiService getApiServiceGalleryImages(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        contextApp = context;

        // Asociamos el interceptor a las peticiones
        final OkHttpClient cli = getClient();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (cli != null) {
            httpClient = cli.newBuilder();
            httpClient.addInterceptor(logging);
        }
        Gson gson=new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_WEBSERVICE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    public static ApiService getApiServiceAboutUs(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        contextApp = context;

        // Asociamos el interceptor a las peticiones
        final OkHttpClient cli = getClient();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (cli != null) {
            httpClient = cli.newBuilder();
            httpClient.addInterceptor(logging);
        }
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_WEBSERVICE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    public static ApiService getApiServiceGetClientBudgets(Context context) {
        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        contextApp = context;

        //Retrofit no sabe deserializar interfaces ni clases abstractas
        //Creamos un objeto que le enseñe cómo diferenciar presupuestos
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Budget.class, new BudgetDeserializer())
                .create();

        // Asociamos el interceptor a las peticiones y añadimos el deserializador al ConverterFactory
        OkHttpClient.Builder httpClient = getClientWithToken();
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(URL_WEBSERVICE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    public static void setClientWithToken(OkHttpClient.Builder loggedClient) {
        if (clientWithToken == null) {
            clientWithToken = loggedClient;
        }
    }

    public static OkHttpClient.Builder getClientWithToken() {
        return clientWithToken;
    }

    @Nullable
    public static OkHttpClient getClient() {
        try {
            InputStream inputStream = contextApp.getResources().openRawResource(R.raw.euroconstrucciones);

            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                Security.addProvider(new BouncyCastleProvider());
            }
            /*for (Provider p : Security.getProviders()) {
                Log.d("Provider", p.toString());
                for(Provider.Service service:p.getServices()){
                    Log.d("Service type",service.getType());
                    Log.d("Service algorithm",service.getAlgorithm());
                }
            }*/
            KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
            keyStore.load(inputStream, "euroconstrucciones".toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore, "euroconstrucciones".toCharArray());
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
