package com.example.storage.service;

import com.example.storage.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product add(Product product);
}
