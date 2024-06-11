package com.example.frontend.activities;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.frontend.R;
import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;
import com.example.frontend.models.ImageGalleryAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageGalleryActivity extends BaseActivity {
    RecyclerView rvImages;
    ImageGalleryAdapter imageGalleryAdapter;
    List<String> imageFiles;
    List<Thread> imageThreads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvImages = findViewById(R.id.rvImages);
        imageFiles = new ArrayList<>();
        imageThreads = new ArrayList<>();

        ApiService service = ApiServiceImpl.getApiServiceGalleryImages(ImageGalleryActivity.this);
        Call<List<String>> call = service.getImageNames();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> validImgs = response.body();
                for (Iterator<String> iterator = validImgs.iterator(); iterator.hasNext(); ) {
                    String str = iterator.next();
                    if (str.toLowerCase().contains("ico") || str.toLowerCase().contains("logo") || str.toLowerCase().contains("obra")) {
                        iterator.remove();
                    }
                }
                imageGalleryAdapter = new ImageGalleryAdapter(validImgs, ImageGalleryActivity.this);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ImageGalleryActivity.this, 2); // 2 columns
                rvImages.setLayoutManager(layoutManager);
                rvImages.setAdapter(imageGalleryAdapter);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ImageGalleryActivity.this);
                builder.setMessage(throwable.getMessage());
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_image_gallery;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}