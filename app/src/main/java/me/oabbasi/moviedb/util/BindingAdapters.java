package me.oabbasi.moviedb.util;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import me.oabbasi.moviedb.network.TheMovieDbApi;

public class BindingAdapters {
    private static final String IMAGE_URL = "imageUrl";

    @BindingAdapter({IMAGE_URL})
    public static void loadImageUrl(ImageView view, String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) return;
        Glide.with(view.getContext()).load(TheMovieDbApi.BASE_IMAGE_URL +imageUrl).into(view);
    }
}
