package com.example.frontend.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.frontend.R;
import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.Budget;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBudgetsActivity extends BaseActivity {

    RecyclerView rvBudgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rvBudgets=findViewById(R.id.rvBudgets);
        SharedPreferences prefs=getPreferences(Context.MODE_PRIVATE);
        String email = prefs.getString("email","");
        if(!email.isEmpty()){
            ApiService service=ApiServiceImpl.getApiServiceGetClientBudgets(MyBudgetsActivity.this,email);
            Call<List<Budget>> call=service.getBudgetsByEmail();
            call.enqueue(new Callback<List<Budget>>() {
                @Override
                public void onResponse(Call<List<Budget>> call, Response<List<Budget>> response) {
                    List<String> budgets = new ArrayList<>();
                    for(Budget b:response.body()){
                        budgets.add(b.toString());
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(MyBudgetsActivity.this, android.R.layout.simple_spinner_item, budgets);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //rvBudgets.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Budget>> call, Throwable throwable) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyBudgetsActivity.this);
                    builder.setMessage(throwable.getMessage());
                    builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                    builder.show();
                }
            });
        }



    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_budgets;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

}