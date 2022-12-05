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
        validateProduct(product);
        productRepository.insert(product);
    }

    private void validateProduct(Product product) {
        if (product.getName().equals("") || product.getName() == null) {
            throw new IllegalArgumentException("[ERROR] 상품의 이름은 반드시 입력해야 합니다.");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 0을 초과하는 금액을 입력해야 합니다.");
        }
    }

    public void update(Product product) {
        productRepository.update(product);
    }

}
