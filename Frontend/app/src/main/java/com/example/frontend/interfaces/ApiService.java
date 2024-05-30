package com.example.frontend.interfaces;

import com.example.frontend.models.BudgetNewBuild;
import com.example.frontend.models.BudgetReformBathroom;
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

    @POST
    Call<User> loginUser(@Body User user);

    @POST
    Call<BudgetReformBathroom> createBathroomBudget(@Body BudgetReformBathroom budgetReformBathroom);

    @POST
    Call<BudgetNewBuild> createNewBuildBudget(@Body BudgetNewBuild budgetNewBuild);
}
