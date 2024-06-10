package com.example.frontend.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.frontend.R;
import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.Budget;
import com.example.frontend.models.BudgetNewBuild;
import com.example.frontend.models.BudgetReformBathroom;
import com.example.frontend.models.BudgetReformKitchen;
import com.example.frontend.models.MyBudgetsAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBudgetsActivity extends BaseActivity implements MyBudgetsAdapter.OnItemClickListener {

    RecyclerView rvBudgets;
    MyBudgetsAdapter myBudgetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rvBudgets = findViewById(R.id.rvBudgets);
        SharedPreferences prefs = getSharedPreferences("emails", Context.MODE_PRIVATE);
        String email = prefs.getString("email", "");
        if (!email.isEmpty()) {
            ApiService service = ApiServiceImpl.getApiServiceGetClientBudgets(MyBudgetsActivity.this);
            Call<List<Budget>> call = service.getBudgetsByEmail();
            call.enqueue(new Callback<List<Budget>>() {
                @Override
                public void onResponse(Call<List<Budget>> call, Response<List<Budget>> response) {
                    myBudgetsAdapter = new MyBudgetsAdapter(response.body(), MyBudgetsActivity.this);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyBudgetsActivity.this);
                    rvBudgets.setLayoutManager(layoutManager);
                    rvBudgets.setAdapter(myBudgetsAdapter);
                }

                @Override
                public void onFailure(Call<List<Budget>> call, Throwable throwable) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyBudgetsActivity.this);
                    Log.d("CallBack Error", String.valueOf(throwable));
                    builder.setMessage(throwable.getMessage());
                    builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                    builder.show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.my_budgets_fail), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemClick(Budget budget) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.generic_data_title));
        builder.setMessage(constructMessage(budget));
        builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private String constructMessage(Budget budget) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.generic_full_name) + budget.getDestinatario() + System.lineSeparator());
        stringBuilder.append(getString(R.string.generic_street) + budget.getDireccion() + System.lineSeparator());
        stringBuilder.append(getString(R.string.generic_postal_code) + budget.getCodigoPostal() + System.lineSeparator());
        stringBuilder.append(getString(R.string.generic_municipality) + budget.getMunicipio() + System.lineSeparator());
        stringBuilder.append(getString(R.string.generic_province) + budget.getProvincia() + System.lineSeparator());
        stringBuilder.append(getString(R.string.generic_reference) + budget.getReferencia() + System.lineSeparator());
        if (budget instanceof BudgetNewBuild) {
            stringBuilder.append(getString(R.string.build_total_m2) + ((BudgetNewBuild) budget).getSuperficie() + System.lineSeparator());
            stringBuilder.append(getString(R.string.build_material_quality) + ((BudgetNewBuild) budget).getCalidad());
        } else if (budget instanceof BudgetReformBathroom) {
            stringBuilder.append(getString(R.string.reform_bathroom_height) + ((BudgetReformBathroom) budget).getAlto() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_bathroom_width) + ((BudgetReformBathroom) budget).getAncho() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_bathroom_length) + ((BudgetReformBathroom) budget).getLargo() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_bathroom_tile_price) + ((BudgetReformBathroom) budget).getPrecioAzulejoM2() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_kitchen_renovation) + ((BudgetReformBathroom) budget).getModifInstalacion() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_bathroom_pieces_add) + ((BudgetReformBathroom) budget).getNumSanitarios());
        } else if (budget instanceof BudgetReformKitchen) {
            stringBuilder.append(getString(R.string.reform_kitchen_height) + ((BudgetReformKitchen) budget).getAlto() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_kitchen_width) + ((BudgetReformKitchen) budget).getAncho() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_kitchen_length) + ((BudgetReformKitchen) budget).getLargo() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_kitchen_tile_price) + ((BudgetReformKitchen) budget).getPrecioAzulejoM2() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_kitchen_renovation) + (((BudgetReformKitchen) budget).getModifInstalacion() == 0 ? getString(R.string.reform_kitchen_renovation_no) : getString(R.string.reform_kitchen_renovation_yes)) + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_kitchen_surface_furniture) + ((BudgetReformKitchen) budget).getMuebleMtrLineales() + System.lineSeparator());
            stringBuilder.append(getString(R.string.reform_kitchen_surface_worktop) + ((BudgetReformKitchen) budget).getEncimeraMtrLineales());
        }
        return stringBuilder.toString();
    }
}