package com.github.antonocean.thebangandroid.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.github.antonocean.thebangandroid.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Tina on 2/8/2017.
 */

public class CustomBindingAdaptor {

    @BindingAdapter("bind:imageUrl")
    public  static void loadImage(ImageView imageView, String url)
    {
        Picasso.with(imageView.getContext()).load(url).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
