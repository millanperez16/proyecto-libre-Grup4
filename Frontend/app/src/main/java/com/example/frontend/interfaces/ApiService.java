package com.example.frontend.interfaces;

import com.example.frontend.models.Municipi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("municipis")
    Call<Municipi> getMunicipi();
}
