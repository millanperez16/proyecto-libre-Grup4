package com.example.frontend.interfaces;

import com.example.frontend.models.Budget;
import com.example.frontend.models.BudgetNewBuild;
import com.example.frontend.models.BudgetReformBathroom;
import com.example.frontend.models.BudgetReformKitchen;
import com.example.frontend.models.Municipi;
import com.example.frontend.models.Token;
import com.example.frontend.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("municipis")
    Call<Municipi> getMunicipi();

    @POST("save")
    Call<User> registerNewUser(@Body User user);

    @POST("authenticate")
    Call<Token> loginUser(@Body User user);

    @POST("saveRefAseo")
    Call<BudgetReformBathroom> createBathroomBudget(@Body BudgetReformBathroom budgetReformBathroom);

    @POST("saveObra")
    Call<BudgetNewBuild> createNewBuildBudget(@Body BudgetNewBuild budgetNewBuild);

    @POST("saveRefCocina")
    Call<BudgetReformKitchen> createKitchenBudget(@Body BudgetReformKitchen budgetReformKitchen);

    @GET("findAll?pagina=1")
    Call<List<Budget>> getBudgetsByEmail();
}
