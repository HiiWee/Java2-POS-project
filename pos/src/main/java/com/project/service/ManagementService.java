package com.project.service;

import com.project.repository.ProductRepository;
import com.project.view.management.ManagementFrame;

public class ManagementService {
    private final ProductRepository productRepository = ProductRepository.getInstance();

    public void refreshTable() {
        ManagementFrame managementFrame = ManagementFrame.getInstance();
        managementFrame.addRowTable(productRepository.findAll());
    }

    public void dropTable() {
        productRepository.drop();
    }

    public void saveList() {
        productRepository.saveList();
    }

    public void insert() {
        productRepository.insert();
    }

    public void clearList() {
        productRepository.clearList();
    }
    public void update(){
        productRepository.update();
    }
}
