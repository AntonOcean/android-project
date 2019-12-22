package com.github.antonocean.thebangandroid.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import java.util.Objects;
import java.util.UUID;


public class CartActivity extends AppCompatActivity {

    static List<Product> products = new ArrayList<>();

    static double total = 0;


    @SuppressLint("SetTextI18n")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView cart_total = (TextView) findViewById(R.id.cart_total);

        assert cart_total != null;
        cart_total.setText("Total: $"+total);

        //change action bar title to search term
        setTitle("Shopping Cart");



        //Display list of products
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        assert recyclerView != null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHandler db = new DatabaseHandler(this);
        List<Product> products = db.getAllProducts();

        recyclerView.setAdapter(new ProductsAdapter(products, R.layout.list_item_cart));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    static public void add(Product p, DatabaseHandler db, Context context){

        products.add(p);
        String price = p.getPrice();

        //delete the $ sign in front
        String price2 = price.substring(1);
        total += Double.parseDouble(price2);

        InputStream is = null;
        try {
            is = (InputStream) new URL(p.getThumbnailImageUrl()).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap binaryImage = BitmapFactory.decodeStream(is);
        try {
            assert is != null;
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
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
            OutputStream stream;
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


}