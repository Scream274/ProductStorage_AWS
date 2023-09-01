package com.example.storage.service.impl;

import com.example.storage.model.Product;
import com.example.storage.repository.ProductRepository;
import com.example.storage.service.ProductService;
import org.springframework.stereotype.Service;

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