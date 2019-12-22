package com.github.antonocean.thebangandroid.db;

import com.github.antonocean.thebangandroid.model.Product;

import java.util.List;

public interface IDatabaseHandler {
    void addProduct(Product contact);
    List<Product> getAllProducts();
    void deleteAllProducts();
}