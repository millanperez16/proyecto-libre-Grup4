package com.example.frontend.interfaces;

import com.example.frontend.models.Municipi;
import com.example.frontend.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("municipis")
    Call<Municipi> getMunicipi();

    @POST("save")
    Call<User> registerNewUser(@Body User user);
}
