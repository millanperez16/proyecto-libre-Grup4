package com.example.frontend;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.BudgetNewBuild;
import com.example.frontend.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BudgetNewBuildActivity extends BaseActivity {
    final Float low = 0.8f;
    final Float medium = 1f;
    final Float high = 1.3f;
    EditText etSurfaceM2;
    RadioGroup rgMaterialsQuality;
    RadioButton rbSelectedQuality;
    Integer surfaceM2;
    Float materialsQuality;
    Button btnNewBuildSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etSurfaceM2 = findViewById(R.id.etBudgetBuildM2);
        rgMaterialsQuality = findViewById(R.id.rgBudgetBuildMaterials);
        btnNewBuildSubmit = findViewById(R.id.btnBudgetBuildSubmit);
        btnNewBuildSubmit.setOnClickListener(v -> {
            if (!validateFormData()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_message_empty));
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            } else {
                Bundle bundle = getIntent().getExtras();
                User user = new User(bundle.getString("nameSurname"), bundle.getString("street"), bundle.getString("postalCode"), bundle.getString("municipality"));
                BudgetNewBuild budgetNewBuild = new BudgetNewBuild(user, surfaceM2, materialsQuality);
                ApiService service = ApiServiceImpl.getApiServiceNewBuildBudget(BudgetNewBuildActivity.this);
                Call<BudgetNewBuild> call = service.createNewBuildBudget(budgetNewBuild);
                call.enqueue(new Callback<BudgetNewBuild>() {
                    @Override
                    public void onResponse(Call<BudgetNewBuild> call, Response<BudgetNewBuild> response) {
                        Toast.makeText(getApplicationContext(), getString(R.string.register_submit_success), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<BudgetNewBuild> call, Throwable throwable) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BudgetNewBuildActivity.this);
                        builder.setMessage(throwable.getMessage());
                        builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                        builder.show();
                    }
                });
            }
        });

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_budget_new_build;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean validateFormData() {
        surfaceM2 = Integer.parseInt(etSurfaceM2.getText().toString());
        rbSelectedQuality = findViewById(rgMaterialsQuality.getCheckedRadioButtonId());
        String selectedQuality = rbSelectedQuality.getText().toString();
        if (selectedQuality.equals("Low") || selectedQuality.equals("Baja") || selectedQuality.equals("Baixa")) {
            materialsQuality = low;
        } else if (selectedQuality.equals("Medium") || selectedQuality.equals("Media") || selectedQuality.equals("Mitjana")) {
            materialsQuality = medium;
        } else if (selectedQuality.equals("High") || selectedQuality.equals("Alta")) {
            materialsQuality = high;
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.register_submit_fail), Toast.LENGTH_SHORT).show();
        }
        return surfaceM2 != null && materialsQuality != null;
    }
}
