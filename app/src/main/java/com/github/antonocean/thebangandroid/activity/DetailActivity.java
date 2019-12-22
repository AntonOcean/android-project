package com.github.antonocean.thebangandroid.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.antonocean.thebangandroid.BR;
import com.github.antonocean.thebangandroid.R;
import com.github.antonocean.thebangandroid.db.DatabaseHandler;
import com.github.antonocean.thebangandroid.model.Product;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    static private DatabaseHandler db;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = new DatabaseHandler(getApplicationContext());

        //get intent info
        Intent intent = getIntent();
        final Product item = (Product) intent.getSerializableExtra("product");

        //set tool bar title
        setTitle(item.getProductName());

        //bind data to product page
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        binding.setVariable(BR.product,item);

        final Context context = getApplicationContext();
        //add to cart button
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_ok));
                fab.setBackgroundTintList (ColorStateList.valueOf(0xFF4CAF50));
                //add product to cart
                CartActivity.add(item, db, context);

            }
        });


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }



}
