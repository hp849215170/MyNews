package com.lsl.mynews.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lsl.mynews.App;
import com.lsl.mynews.R;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2016/6/6.
 */
public class ImageLoaderUtil {

    public static void display(ImageView imageView, String url) {
        Glide.with(App.getContext()).load(url)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(imageView);
    }
}
