package com.example.frontend;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.Element;
import com.example.frontend.models.Municipi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenericBudgetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> provinces = new ArrayList<>(Arrays.asList("Barcelona"));
        Spinner spProvince=findViewById(R.id.spProvince);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, provinces);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProvince.setAdapter(adapter);

        AutoCompleteTextView actv = findViewById(R.id.actvMunicipality);
        String like = actv.getText().toString();
        ApiService service = ApiServiceImpl.getApiServiceInstance(like);
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
        AutoCompleteTextView actvMunicipality = (AutoCompleteTextView) findViewById(R.id.actvMunicipality);
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
}