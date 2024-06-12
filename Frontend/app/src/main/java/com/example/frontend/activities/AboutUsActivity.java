package com.example.frontend.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.frontend.R;
import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends BaseActivity {

    TextView tvWallOfText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvWallOfText = findViewById(R.id.tvAboutUsText);
        ApiService service = ApiServiceImpl.getApiServiceAboutUs(AboutUsActivity.this);
        Call<String> call = service.getAboutUsText();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                tvWallOfText.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AboutUsActivity.this);
                builder.setMessage(throwable.getMessage());
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_about_us;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}