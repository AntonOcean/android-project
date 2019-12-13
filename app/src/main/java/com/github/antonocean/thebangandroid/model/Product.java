package com.github.antonocean.thebangandroid.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Product implements Serializable {
    @SerializedName("price")
    private String price;

    @SerializedName("productName")
    private String productName;


    @SerializedName("brandName")
    private String brandName;



    @SerializedName("thumbnailImageUrl")
    private String thumbnailImageUrl;

    private Bitmap image = null;


    public Product(String price, String productName, String brandName, String thumbnailImageUrl) {
        this.price = price;
        this.productName = productName;
        this.brandName = brandName;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public Product(String price, String productName, String brandName, String thumbnailImageUrl, Bitmap image) {
        this.price = price;
        this.productName = productName;
        this.brandName = brandName;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.image = image;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }




    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
}