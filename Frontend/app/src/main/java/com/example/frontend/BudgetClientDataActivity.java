package com.example.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;

import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.Element;
import com.example.frontend.models.Municipi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BudgetClientDataActivity extends BaseActivity {

    EditText etNameSurnames;
    EditText etStreet;
    EditText etPostalCode;
    AutoCompleteTextView actvMunicipality;
    Spinner spProvince;
    Button btnBuild;
    Button btnBathroom;
    Button btnKitchen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etNameSurnames = findViewById(R.id.etNameSurnames);
        etStreet = findViewById(R.id.etStreet);
        etPostalCode = findViewById(R.id.etPostalCode);
        actvMunicipality = findViewById(R.id.actvMunicipality);
        spProvince = findViewById(R.id.spProvince);

        btnBuild = findViewById(R.id.btnNewBuild);
        btnBuild.setOnClickListener(v -> {
            Intent intent;
            if ((intent = fillIntentClientData(BudgetNewBuildActivity.class)) != null) {
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_message_empty));
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            }
        });

        btnBathroom = findViewById(R.id.btnReformBathroom);
        btnBathroom.setOnClickListener(v -> {
            Intent intent;
            if ((intent = fillIntentClientData(BudgetReformBathroomActivity.class)) != null) {
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_message_empty));
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            }
        });

        btnKitchen = findViewById(R.id.btnReformKitchen);
        btnKitchen.setOnClickListener(v -> {
            Intent intent;
            if ((intent = fillIntentClientData(BudgetReformKitchenActivity.class)) != null) {
                startActivity(intent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getString(R.string.alert_message_empty));
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            }
        });

        ArrayList<String> provinces = new ArrayList<>(Arrays.asList("Barcelona"));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProvince.setAdapter(adapter);

        AutoCompleteTextView actv = findViewById(R.id.actvMunicipality);
        String like = actv.getText().toString();
        ApiService service = ApiServiceImpl.getApiServiceMunicipi(like);
        Call<Municipi> call = service.getMunicipi();
        call.enqueue(new Callback<Municipi>() {
            @Override
            public void onResponse(Call<Municipi> call, Response<Municipi> response) {
                if (response.body() != null) {
                    loadDataList(response.body());
                }
            }

            @Override
            public void onFailure(Call<Municipi> call, Throwable throwable) {
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_budget_client_data;
    }

    private void loadDataList(Municipi municipi) {
        AutoCompleteTextView actvMunicipality = findViewById(R.id.actvMunicipality);
        actvMunicipality.setThreshold(3);
        List<Element> elements = municipi.getElements();
        List<String> names = new ArrayList<>();
        for (Element e : elements) {
            names.add(e.getMunicipiNom());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        actvMunicipality.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    private Intent fillIntentClientData(Class nextForm) {
        if (!validateFormData()) {
            return null;
        }
        Intent intent = new Intent(this, nextForm);
        intent.putExtra("nameSurname", etNameSurnames.getText().toString());
        intent.putExtra("street", etStreet.getText().toString());
        intent.putExtra("postalCode", etPostalCode.getText().toString());
        intent.putExtra("municipality", actvMunicipality.getText().toString());
        intent.putExtra("province",spProvince.getSelectedItem().toString());
        return intent;
    }

    private boolean validateFormData() {
        String name = etNameSurnames.getText().toString();
        String street = etStreet.getText().toString();
        String postalCode = etPostalCode.getText().toString();
        String municipality = actvMunicipality.getText().toString();
        return !name.isEmpty() && !street.isEmpty() && !postalCode.isEmpty() && !municipality.isEmpty();
    }
}