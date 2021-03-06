package com.github.antonocean.thebangandroid.model;

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


    public Product(String price, String productName, String brandName, String thumbnailImageUrl) {
        this.price = price;
        this.productName = productName;
        this.brandName = brandName;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }


    public String getBrandName() {
        return brandName;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
}