package com.github.antonocean.thebangandroid.binding;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.github.antonocean.thebangandroid.R;
import com.squareup.picasso.Picasso;


public class CustomBindingAdaptor {

    @BindingAdapter("bind:imageUrl")
    public  static void loadImage(ImageView imageView, String url)
    {
        Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
