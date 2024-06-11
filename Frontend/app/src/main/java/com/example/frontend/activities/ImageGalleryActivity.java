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
import java.util.List;

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
                for (String img : response.body()) {
                    imageThreads.add(new Thread(new UrlThread(img, service)));
                }
                for (Thread t : imageThreads) {
                    t.start();
                }
                for (Thread t : imageThreads) {
                    try {
                        t.join();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                imageGalleryAdapter = new ImageGalleryAdapter(imageFiles, ImageGalleryActivity.this);
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

    private void addImageFile(String fileName, ApiService service) {
        Call<String> call = service.getImageFile(fileName);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("ADD IMAGE FILE", response.body());
                imageFiles.add(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.d("REQUEST", String.valueOf(call.request()));
                AlertDialog.Builder builder = new AlertDialog.Builder(ImageGalleryActivity.this);
                builder.setMessage(throwable.getMessage());
                builder.setPositiveButton(getString(R.string.alert_button_ok), (dialog, which) -> dialog.cancel());
                builder.show();
            }
        });
    }

    class UrlThread implements Runnable {
        String fileName;
        ApiService service;

        UrlThread(String fileName, ApiService service) {
            this.fileName = fileName;
            this.service = service;
        }

        @Override
        public void run() {
            addImageFile(fileName, service);
        }
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