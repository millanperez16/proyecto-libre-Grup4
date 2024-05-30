package com.example.frontend;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    TextView tvLoginRegister;
    EditText etLoginMail;
    EditText etLoginPwd;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvLoginRegister = findViewById(R.id.tvLoginRegister);
        btnLogin = findViewById(R.id.btnLogin);
        etLoginMail = findViewById(R.id.etLoginMail);
        etLoginPwd = findViewById(R.id.etLoginPwd);

        tvLoginRegister.setOnClickListener(v -> goToRegisterMenu());
        btnLogin.setOnClickListener(v -> loginProcess());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public void goToRegisterMenu() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public boolean validateCredentials() {
        if (etLoginMail.getText().toString().isEmpty() || etLoginPwd.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.alert_message_empty));
            builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
            builder.show();
            return false;
        }
        return true;
    }

    public void loginProcess() {
        if (validateCredentials()) {
            User user = new User(etLoginMail.getText().toString(), etLoginPwd.getText().toString());
            ApiService service = ApiServiceImpl.getApiServiceLoginUser(this);
            Call<User> call = service.loginUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(getApplicationContext(), getString(R.string.register_submit_success), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable throwable) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage(throwable.getMessage());
                    builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                    builder.show();
                }
            });

        }
    }
}