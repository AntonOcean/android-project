package com.github.antonocean.thebangandroid.Retrofit;

import com.github.antonocean.thebangandroid.model.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("Search")
    Call<ProductsResponse> getSearch(@Query("term") String search_term, @Query("key") String apiKey);

}