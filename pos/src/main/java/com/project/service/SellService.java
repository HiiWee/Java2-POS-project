package com.project.service;

import com.project.domain.Product;
import com.project.repository.ProductRepository;
import java.util.List;

public class SellService {
    private final ProductRepository productRepository = ProductRepository.getInstance();

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }
}
