package com.github.antonocean.thebangandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.github.antonocean.thebangandroid.model.Product;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productsManager.db";
    private static final String TABLE_PRODUCTS = "products";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_BRAND = "brand";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IMAGE = "image";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_BRAND + " TEXT, "
                + KEY_PRICE + " TEXT, "
                + KEY_IMAGE + " BLOB" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        onCreate(db);
    }

    @Override
    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.getProductName());
        values.put(KEY_BRAND, product.getBrandName());
        values.put(KEY_PRICE, product.getPrice());


//        InputStream is = null;
//        try {
//            is = (InputStream) new URL(product.getThumbnailImageUrl()).getContent();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Bitmap binaryImage = BitmapFactory.decodeStream(is);
//        try {
//            is.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        values.put(KEY_IMAGE, binaryImage.toString());
        values.put(KEY_IMAGE, "url");

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<Product>();
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(1);
                String brand = cursor.getString(2);
                String price = cursor.getString(3);
                String image = cursor.getString(4);

                Product product = new Product(price, name, brand, "url");
                productList.add(product);
            } while (cursor.moveToNext());
        }

        return productList;
    }

    @Override
    public void deleteProduct(Product contact) {

    }
}