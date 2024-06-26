package com.example.frontend.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.frontend.R;
import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.BudgetReformBathroom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BudgetReformBathroomActivity extends BaseActivity {

    EditText etBathroomHeight;
    EditText etBathroomWidth;
    EditText etBathroomLength;
    EditText etBathroomTiles;
    RadioGroup rgPiecesAdd;
    RadioGroup rgPiecesRemove;
    Float height;
    Float width;
    Float length;
    Float tiles;
    RadioButton rbAdd;
    Integer piecesAdd;
    RadioButton rbRemove;
    Integer piecesRemove;
    Button btnReformBathroomSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etBathroomHeight=findViewById(R.id.etBudgetReformBathroomHeight);
        etBathroomWidth=findViewById(R.id.etBudgetReformBathroomWidth);
        etBathroomLength=findViewById(R.id.etBudgetReformBathroomLength);
        etBathroomTiles=findViewById(R.id.etBudgetReformBathroomTiles);
        rgPiecesAdd=findViewById(R.id.rgBathroomPiecesAdd);
        rgPiecesRemove=findViewById(R.id.rgBathroomPiecesRemove);

        btnReformBathroomSubmit = findViewById(R.id.btnReformBathroomSubmit);
        btnReformBathroomSubmit.setOnClickListener(v -> {
            if (!validateFormData()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_message_empty));
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            } else {
                Bundle bundle = getIntent().getExtras();
                BudgetReformBathroom bathroomBudget = new BudgetReformBathroom(bundle.getString("nameSurname"), bundle.getString("street"), bundle.getString("postalCode"), bundle.getString("municipality"),bundle.getString("province"), height, width, length, tiles, piecesAdd, piecesRemove);
                ApiService service = ApiServiceImpl.getApiServiceBathroomBudget(BudgetReformBathroomActivity.this);
                Call<BudgetReformBathroom> call = service.createBathroomBudget(bathroomBudget);
                call.enqueue(new Callback<BudgetReformBathroom>() {
                    @Override
                    public void onResponse(Call<BudgetReformBathroom> call, Response<BudgetReformBathroom> response) {
                        Toast.makeText(getApplicationContext(), getString(R.string.register_submit_success), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<BudgetReformBathroom> call, Throwable throwable) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BudgetReformBathroomActivity.this);
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
        return R.layout.activity_budget_reform_bathroom;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private boolean validateFormData() {
        height = Float.parseFloat(etBathroomHeight.getText().toString());
        width = Float.parseFloat(etBathroomWidth.getText().toString());
        length = Float.parseFloat(etBathroomLength.getText().toString());
        tiles = Float.parseFloat(etBathroomTiles.getText().toString());
        rbAdd = findViewById(rgPiecesAdd.getCheckedRadioButtonId());
        piecesAdd = Integer.parseInt(rbAdd.getText().toString());
        rbRemove = findViewById(rgPiecesRemove.getCheckedRadioButtonId());
        piecesRemove = Integer.parseInt(rbRemove.getText().toString());
        return !height.isNaN() && !width.isNaN() && !length.isNaN() && !tiles.isNaN() && piecesAdd != null && piecesRemove != null;
    }
}
