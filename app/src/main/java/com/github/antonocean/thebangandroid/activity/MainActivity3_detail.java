package com.github.antonocean.thebangandroid.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.github.antonocean.thebangandroid.BR;
import com.github.antonocean.thebangandroid.R;
import com.github.antonocean.thebangandroid.model.Product;

public class MainActivity3_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        //get intent info
        Intent intent = getIntent();
        final Product item = (Product) intent.getSerializableExtra("product");

        //set tool bar title
        setTitle(item.getProductName());

        //bind data to product page
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main3);

        binding.setVariable(BR.product,item);

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageURI(Uri.parse(item.getThumbnailImageUrl()));

        binding.setVariable(R.id.imageView, img);

        //add to cart button
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, item.getProductName()+" added to your Cart!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                fab.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_ok));
                fab.setBackgroundTintList (ColorStateList.valueOf(0xFF4CAF50));
                //add product to cart
                MainActivity4_cart.add(item);

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
