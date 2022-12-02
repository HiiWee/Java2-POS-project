package com.project.service;

import com.project.repository.ProductRepository;
import com.project.view.sell.DetailTableFrame;

public class SellService {
    private final ProductRepository productRepository = ProductRepository.getInstance();

    public void initSellPage() {
        DetailTableFrame detailTableFrame = DetailTableFrame.getInstance();
        detailTableFrame.initProduct(productRepository.findAll());
    }
}
