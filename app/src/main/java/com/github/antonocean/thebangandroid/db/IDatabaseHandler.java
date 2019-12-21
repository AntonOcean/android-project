package com.github.antonocean.thebangandroid.db;

import com.github.antonocean.thebangandroid.model.Product;

import java.util.List;

public interface IDatabaseHandler {
    public void addProduct(Product contact);
    public List<Product> getAllProducts();
    public void deleteAllProducts();
}