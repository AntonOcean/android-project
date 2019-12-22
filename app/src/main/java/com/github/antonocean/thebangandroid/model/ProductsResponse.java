package com.github.antonocean.thebangandroid.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ProductsResponse {

    @SerializedName("results")
    private List<Product> results;


    public List<Product> getResults() {
        return results;
    }

}