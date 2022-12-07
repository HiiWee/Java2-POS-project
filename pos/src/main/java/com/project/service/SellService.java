package com.project.service;

import com.project.controller.dto.GraphDto;
import com.project.controller.dto.SellProductInBillDto;
import com.project.domain.Product;
import com.project.domain.SeatProduct;
import com.project.domain.Sell;
import com.project.domain.SellProduct;
import com.project.repository.ProductRepository;
import com.project.repository.SeatProductRepository;
import com.project.repository.SellProductRepository;
import com.project.repository.SellRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SellService {
    private final ProductRepository productRepository = ProductRepository.getInstance();
    private final SeatProductRepository seatProductRepository = SeatProductRepository.getInstance();
    private final SellRepository sellRepository = SellRepository.getInstance();
    private final SellProductRepository sellProductRepository = SellProductRepository.getInstance();

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public List<SeatProduct> findAllSeatProductById(Long id) {
        System.out.println(id);
        return seatProductRepository.findAllById(id);
    }

    public void saveSeatProducts(final List<SeatProduct> seatProducts, Long tableNumber) {
        if (seatProducts.isEmpty()) {
            throw new IllegalArgumentException("상품을 담아야 주문할 수 있습니다.");
        }
        seatProductRepository.deleteAllBySeatId(tableNumber);
        seatProductRepository.saveAll(seatProducts);
    }

    public void paySeatProduct(final List<SeatProduct> seatProducts) {
        if (seatProducts.isEmpty()) {
            throw new IllegalArgumentException("상품을 주문하지 않으면 결제할 수 없습니다.]");
        }
        Sell savedSell = sellRepository.save(new Sell(1L));
        sellProductRepository.saveAll(convertSeatToSellProduct(seatProducts, savedSell.getId()));
    }

    private List<SellProduct> convertSeatToSellProduct(final List<SeatProduct> seatProducts, final Long sellId) {
        return seatProducts.stream()
                .map(seatProduct -> new SellProduct(seatProduct.getProductName(),
                        seatProduct.getQuantity(),
                        seatProduct.getPrice(),
                        seatProduct.getProductId(),
                        sellId))
                .collect(Collectors.toList());
    }

    public void clearSeatProducts(final long seatId) {
        seatProductRepository.deleteAllBySeatId(seatId);
    }

    public boolean checkAllProduct(final List<SeatProduct> seatProducts, final Long tableId) {
        List<SeatProduct> actualSeatProducts = findAllSeatProductById(tableId);
        return actualSeatProducts.size() == seatProducts.size();
    }

    public List<SellProductInBillDto> findAllProductById(final Long sellId) {
        return sellRepository.findAllById(sellId);
    }

    public void deleteBySellId(final long sellId) {
        sellRepository.deleteBySellId(sellId);
    }

}
