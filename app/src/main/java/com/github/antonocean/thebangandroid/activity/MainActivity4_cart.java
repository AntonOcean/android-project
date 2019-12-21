package com.github.antonocean.thebangandroid.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.antonocean.thebangandroid.R;
import com.github.antonocean.thebangandroid.binding.ProductsAdapter;
import com.github.antonocean.thebangandroid.db.DatabaseHandler;
import com.github.antonocean.thebangandroid.model.Product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static butterknife.ButterKnife.Finder.arrayOf;


public class MainActivity4_cart extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    static List<Product> products = new ArrayList<Product>();

    static double total = 0;


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

        DatabaseHandler db = new DatabaseHandler(this);
        List<Product> products = db.getAllProducts();
/*
        TODO: products = getFromdB
*/

        recyclerView.setAdapter(new ProductsAdapter(products, R.layout.list_item_cart, getApplicationContext()));

//        for (Product product: products) {
//            InputStream is = null;
//            try {
//                is = (InputStream) new URL(product.getThumbnailImageUrl()).getContent();
//            } catch (IOException e) {
//                e.printStackTrace();
//                break;
//            }
//            Bitmap d = BitmapFactory.decodeStream(is);
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    static public void add(Product p, DatabaseHandler db, Context context){

//        DatabaseHandler DBHelper = new DatabaseHandler(getApplicationContext());

        products.add(p);
        String price = p.getPrice();

        //delete the $ sign in front
        String price2 = price.substring(1,price.length());
        total += Double.parseDouble(price2);

        InputStream is = null;
        try {
            is = (InputStream) new URL(p.getThumbnailImageUrl()).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap binaryImage = BitmapFactory.decodeStream(is);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
//            e.printStackTrace();
            Toast toast = Toast.makeText(
                    context,
                    "Товар уже добавлен в корзину",
                    Toast.LENGTH_SHORT);
            toast.show();
            return;
        }


        String path = Environment.getExternalStorageDirectory().toString();
        File file = new File(path, UUID.randomUUID().toString() +".jpg");
        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            binaryImage.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        p.setThumbnailImageUrl(savedImageURI.toString());

        db.addProduct(p);
        Toast toast = Toast.makeText(
                context,
                "Товар добавлен в корзину",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void clearCart(View v){
        products.clear();
        DatabaseHandler db = new DatabaseHandler(this);
        db.deleteAllProducts();

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