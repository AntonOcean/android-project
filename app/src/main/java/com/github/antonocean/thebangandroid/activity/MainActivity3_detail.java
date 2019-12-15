package com.github.antonocean.thebangandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.BindingAdapter;
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
import com.github.antonocean.thebangandroid.databinding.ActivityMain3Binding;
import com.github.antonocean.thebangandroid.db.DatabaseHandler;
import com.github.antonocean.thebangandroid.model.Product;
import com.squareup.picasso.Picasso;

public class MainActivity3_detail extends AppCompatActivity {

    static private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        db = new DatabaseHandler(getApplicationContext());

        //get intent info
        Intent intent = getIntent();
        final Product item = (Product) intent.getSerializableExtra("product");

        //set tool bar title
        setTitle(item.getProductName());

        //bind data to product page
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main3);

//        ImageView img = ((ActivityMain3Binding) binding).imageView;
//        img.setImageURI(Uri.parse(item.getThumbnailImageUrl()));

        binding.setVariable(BR.product,item);

//
//        binding.setVariable(R.id.imageView, img);
        final Context context = getApplicationContext();
        //add to cart button
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_ok));
                fab.setBackgroundTintList (ColorStateList.valueOf(0xFF4CAF50));
                //add product to cart
                MainActivity4_cart.add(item, db, context);

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



}
