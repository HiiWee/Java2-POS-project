package com.project.controller;

import com.project.service.ProductService;
import com.project.view.common.NormalButton;
import com.project.view.sell.DetailTableFrame;
import com.project.view.sell.ProductListPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class ProductController {
    private final ProductService productService = new ProductService();
    private final DetailTableFrame detailTableFrame = DetailTableFrame.getInstance();

    public void initSellPage() {
        productService.initSellPage();
        addActionOnProductListPanels(detailTableFrame.getProductListPanels());

    }

    private void addActionOnProductListPanels(final ProductListPanel[] productListPanels) {
        for (ProductListPanel productListPanel : productListPanels) {
            productListPanel.getAddButton()
                    .addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(final MouseEvent e) {
                            ProductListPanel panel = (ProductListPanel) ((NormalButton) e.getSource()).getParentComponent();
                            detailTableFrame.putProduct(panel);
                        }
                    });
            productListPanel.getMinusButton()
                    .addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(final MouseEvent e) {
                            ProductListPanel panel = (ProductListPanel) ((NormalButton) e.getSource()).getParentComponent();
                            detailTableFrame.minusProduct(panel);
                        }
                    });
        }
    }
}
