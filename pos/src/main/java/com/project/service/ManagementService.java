package com.project.service;

import com.project.domain.Product;
import com.project.repository.ProductRepository;
import java.util.List;

public class ManagementService {
    private final ProductRepository productRepository = ProductRepository.getInstance();

    public List<Product> fineAllProducts() {
     return productRepository.findAll();
    }

    public void dropTable() {
        productRepository.dropById(productRepository.findById());
    }

    public void insert(Product product) {
        productRepository.insert(product);
    }

    public void update(Product product) {
        productRepository.update(product);
    }

}
