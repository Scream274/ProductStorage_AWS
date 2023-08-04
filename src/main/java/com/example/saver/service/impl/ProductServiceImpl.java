package com.example.saver.service.impl;

import com.example.saver.model.Product;
import com.example.saver.repository.ProductRepository;
import com.example.saver.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product add(Product product) {
        return repository.save(product);
    }
}