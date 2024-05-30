package com.example.frontend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.BudgetReformBathroom;
import com.example.frontend.models.BudgetReformKitchen;
import com.example.frontend.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BudgetReformKitchenActivity extends BaseActivity {

    EditText etKitchenHeight;
    EditText etKitchenWidth;
    EditText etKitchenLength;
    EditText etKitchenTiles;
    EditText etKitchenSurfaceFurniture;
    EditText etKitchenSurfaceWorktop;
    RadioGroup rgKitchenReno;
    RadioButton rbKitchenReno;
    final Integer kitchenRenoFactor = 1000;
    Integer selectedFactor;
    Float height;
    Float width;
    Float length;
    Float tiles;
    Integer surfaceFurniture;
    Integer surfaceWorktop;
    Button btnReformKitchenSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etKitchenHeight = findViewById(R.id.etBudgetReformKitchenHeight);
        etKitchenWidth = findViewById(R.id.etBudgetReformKitchenWidth);
        etKitchenLength = findViewById(R.id.etBudgetReformKitchenLength);
        etKitchenTiles = findViewById(R.id.etBudgetReformKitchenTiles);
        etKitchenSurfaceFurniture = findViewById(R.id.etBudgetReformKitchenFurnitureM);
        etKitchenSurfaceWorktop = findViewById(R.id.etBudgetReformKitchenWorktopM);
        rgKitchenReno = findViewById(R.id.rgReformKitchenReno);

        btnReformKitchenSubmit = findViewById(R.id.btnReformKitchenSubmit);
        btnReformKitchenSubmit.setOnClickListener(view -> {
            if (!validateFormData()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_message_empty));
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            } else {
                Bundle bundle = getIntent().getExtras();
                User user = new User(bundle.getString("nameSurname"), bundle.getString("street"), bundle.getString("postalCode"), bundle.getString("municipality"));
                BudgetReformKitchen kitchenBudget = new BudgetReformKitchen(user, height, width, length, tiles, selectedFactor, surfaceFurniture, surfaceWorktop);
                ApiService service = ApiServiceImpl.getApiServiceKitchenBudget(BudgetReformKitchenActivity.this);
                Call<BudgetReformKitchen> call = service.createKitchenBudget(kitchenBudget);
                call.enqueue(new Callback<BudgetReformKitchen>() {
                    @Override
                    public void onResponse(Call<BudgetReformKitchen> call, Response<BudgetReformKitchen> response) {
                        Toast.makeText(getApplicationContext(), getString(R.string.register_submit_success), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<BudgetReformKitchen> call, Throwable throwable) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BudgetReformKitchenActivity.this);
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
        return R.layout.activity_budget_reform_kitchen;
    }

    private boolean validateFormData() {
        height = Float.parseFloat(etKitchenHeight.getText().toString());
        width = Float.parseFloat(etKitchenWidth.getText().toString());
        length = Float.parseFloat(etKitchenLength.getText().toString());
        tiles = Float.parseFloat(etKitchenTiles.getText().toString());
        rbKitchenReno = findViewById(rgKitchenReno.getCheckedRadioButtonId());
        String selectedReno = rbKitchenReno.getText().toString();
        selectedFactor = selectedReno.equals("Yes") ? kitchenRenoFactor : 0;
        surfaceFurniture = Integer.parseInt(etKitchenSurfaceFurniture.getText().toString());
        surfaceWorktop = Integer.parseInt(etKitchenSurfaceWorktop.getText().toString());
        return !height.isNaN() && !width.isNaN() && !length.isNaN() && !tiles.isNaN() && selectedFactor != null && surfaceFurniture != null && surfaceWorktop != null;
    }
}