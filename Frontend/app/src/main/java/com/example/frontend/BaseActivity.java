package com.example.frontend;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.frontend.models.LocaleHelper;

public abstract class BaseActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        configureToolbar();
    }

    protected abstract int getLayoutResource();

    private void configureToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            FragmentManager fm = getSupportFragmentManager();
            if (fm != null && fm.getBackStackEntryCount() > 0) {
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            } else {
                finish();
            }
            return true;
        } else if (item.getItemId() == R.id.action_english) {
            LocaleHelper.setLocale(getApplicationContext(), "en");
            return true;
        } else if (item.getItemId() == R.id.action_spanish) {
            LocaleHelper.setLocale(getApplicationContext(), "es");
            return true;
        } else if (item.getItemId() == R.id.action_catalan) {
            LocaleHelper.setLocale(getApplicationContext(), "ca");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

