package com.example.frontend;

import androidx.appcompat.app.AlertDialog;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    EditText etName;
    EditText etMail;
    EditText etPhone;
    EditText etPasswd;
    EditText etConfirmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        etName = findViewById(R.id.etRegisterName);
        etMail = findViewById(R.id.etRegisterEmail);
        etPhone = findViewById(R.id.etRegisterPhone);
        etPasswd = findViewById(R.id.etRegisterPwd);
        etConfirmPwd = findViewById(R.id.etRegisterPwdConfirm);

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> createUser());

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void createUser() {
        if (validateUser()) {
            User user = new User(etName.getText().toString(), etMail.getText().toString(), etPhone.getText().toString(), etPasswd.getText().toString());
            ApiService service = ApiServiceImpl.getApiServiceNewUser(user);
            Call<User> call = service.registerNewUser(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(getApplicationContext(), "Register successful", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable throwable) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public boolean validateUser() {
        if (etName.getText().toString().isEmpty() || etMail.getText().toString().isEmpty() || etPhone.getText().toString().isEmpty() || etPasswd.getText().toString().isEmpty() || etConfirmPwd.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.alert_message_empty));
            builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
            builder.show();
            return false;
        }
        if (!etPasswd.getText().toString().equals(etConfirmPwd.getText().toString())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.alert_message_pwd));
            builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
            builder.show();
            return false;
        }
        return true;
    }
}