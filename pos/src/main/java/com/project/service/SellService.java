package com.project.service;

import com.project.domain.Product;
import com.project.domain.SeatProduct;
import com.project.repository.ProductRepository;
import com.project.repository.SeatProductRepository;
import java.util.List;

public class SellService {
    private final ProductRepository productRepository = ProductRepository.getInstance();
    private final SeatProductRepository seatProductRepository = SeatProductRepository.getInstance();

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public void saveSeatProducts(final List<SeatProduct> seatProducts) {
        if (seatProducts.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 주문할 상품이 없습니다.");
        }
        seatProductRepository.deleteAllBySeatId(seatProducts.get(0).getSeatId());
        seatProductRepository.saveSeatProductList(seatProducts);
    }
}
