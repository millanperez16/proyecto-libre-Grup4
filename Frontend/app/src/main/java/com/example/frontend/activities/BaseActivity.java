package com.example.frontend.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.FragmentManager;

import com.example.frontend.R;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public abstract class BaseActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        configureToolbar();
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    protected abstract int getLayoutResource();

    private void configureToolbar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getLayoutResource() != R.layout.activity_main) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
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
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("en"));
            return true;
        } else if (item.getItemId() == R.id.action_spanish) {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("es"));
            return true;
        } else if (item.getItemId() == R.id.action_catalan) {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("ca"));
            return true;
        } else if (item.getItemId() == R.id.action_new_budget) {
            startActivity(new Intent(this, BudgetClientDataActivity.class));
            return true;
        } else if (item.getItemId() == R.id.action_my_budgets) {
            startActivity(new Intent(this, MyBudgetsActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}

