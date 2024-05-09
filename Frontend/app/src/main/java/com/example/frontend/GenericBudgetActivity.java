package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.Municipi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenericBudgetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_budget);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ApiService service = ApiServiceImpl.getApiServiceInstance();
        Call<Municipi> call = service.getMunicipi();
        call.enqueue(new Callback<Municipi>() {
            @Override
            public void onResponse(Call<Municipi> call, Response<Municipi> response) {
                loadDataList(response.body());
            }

            @Override
            public void onFailure(Call<Municipi> call, Throwable throwable) {
                Toast.makeText(GenericBudgetActivity.this, "Unable to load elements", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_generic_budget;
    }

    private void loadDataList(Municipi municipi) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}