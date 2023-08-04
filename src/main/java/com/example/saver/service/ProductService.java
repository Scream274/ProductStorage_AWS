package com.example.saver.service;

import com.example.saver.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product add(Product product);
}
