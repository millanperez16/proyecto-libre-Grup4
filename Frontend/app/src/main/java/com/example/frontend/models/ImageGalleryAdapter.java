package com.example.frontend.models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frontend.R;
import com.example.frontend.impl.ApiServiceImpl;
import com.example.frontend.interfaces.ApiService;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.CustomViewHolder> {

    private List<String> imageUrls;
    private Context context;

    public ImageGalleryAdapter(List<String> imageUrls, Context context) {
        this.imageUrls = imageUrls;
        this.context = context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        public final View myView;
        ImageView imageView;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
            imageView = myView.findViewById(R.id.imageView);
        }

        public void bind(String imageUrl) {
            ApiService service = ApiServiceImpl.getApiServiceGalleryImages(context);
            Call<ResponseBody> call = service.getImageFile(imageUrl);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        try {
                            Glide.with(context)
                                    .load(response.body().bytes())
                                    .into(imageView);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Log.e("Glide Error", "Response not successful");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Glide Error", t.getMessage(), t);
                }
            });
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.image_gallery_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageGalleryAdapter.CustomViewHolder holder, int position) {
        String imageUrl = imageUrls.get(position);
        holder.bind(imageUrl);
    }

    @Override
    public int getItemCount() {
        return imageUrls != null ? imageUrls.size() : 0;
    }
}
