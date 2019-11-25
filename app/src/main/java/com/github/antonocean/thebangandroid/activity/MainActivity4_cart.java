package com.github.antonocean.thebangandroid.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.antonocean.thebangandroid.R;
import com.github.antonocean.thebangandroid.binding.ProductsAdapter;
import com.github.antonocean.thebangandroid.model.Product;

import java.util.ArrayList;
import java.util.List;


public class MainActivity4_cart extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    static List<Product> products = new ArrayList<Product>();

    static double total = 0;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();

        TextView cart_total = (TextView) findViewById(R.id.cart_total);

        cart_total.setText("Total: $"+total);

        //change action bar title to search term
        setTitle("Shopping Cart");



        //Display list of products
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setAdapter(new ProductsAdapter(products, R.layout.list_item_cart, getApplicationContext()));





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    static public void add(Product p){


        products.add(p);
        String price = p.getPrice();

        //delete the $ sign in front
        String price2 = price.substring(1,price.length());
        total += Double.parseDouble(price2);



    }

    public void clearCart(View v){
        products.clear();
        total = 0;
        //refresh
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    static public void remove(Product p){
        products.remove(p);
    }






}