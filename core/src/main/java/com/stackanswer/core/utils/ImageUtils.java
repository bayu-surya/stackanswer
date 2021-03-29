package com.stackanswer.core.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageUtils {

    public static void fromUrlWithSize(Context context, String url, ImageView imageView, int width, int height) {
        RequestOptions options = new RequestOptions()
                .override(width,height);
        Glide.with(context).load(url).apply(options).dontAnimate().into(imageView);
    }
}