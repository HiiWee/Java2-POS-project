package com.project.service;

import com.project.controller.dto.TotalSellProductDto;
import com.project.repository.SellProductRepository;
import java.util.List;

public class BillCheckService {
    private final SellProductRepository sellProductRepository = SellProductRepository.getInstance();

    public List<TotalSellProductDto> findAllByTotalSellProduct() {
        return sellProductRepository.findAllByTotalSellProduct();
    }
}
