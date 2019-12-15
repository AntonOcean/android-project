package com.github.antonocean.thebangandroid.binding;

import android.databinding.BindingAdapter;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.github.antonocean.thebangandroid.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;

/**
 * Created by Tina on 2/8/2017.
 */

public class CustomBindingAdaptor {

    @BindingAdapter("bind:imageUrl")
    public  static void loadImage(ImageView imageView, String url)
    {
        if (URLUtil.isValidUrl(url)) {
            Picasso.with(imageView.getContext()).load(url).placeholder(R.mipmap.ic_launcher).into(imageView);
        } else {
            Picasso.with(imageView.getContext()).load(new File(url)).placeholder(R.mipmap.ic_launcher).into(imageView);
        }
//        Picasso.with(imageView.getContext()).load(url).placeholder(R.mipmap.ic_launcher).into(imageView);
//        Picasso.with(imageView.getContext()).load(new File(url)).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
