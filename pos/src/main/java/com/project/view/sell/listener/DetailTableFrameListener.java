package com.project.view.sell.listener;

import com.project.view.common.NormalButton;
import com.project.view.sell.DetailTableFrame;
import com.project.view.sell.ProductListPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DetailTableFrameListener {
    private final DetailTableFrame detailTableFrame = DetailTableFrame.getInstance();

    public DetailTableFrameListener() {
    }

    public void addActionOnDetailTableFrameListener() {
        for (ProductListPanel productListPanel : detailTableFrame.getProductListPanels()) {
            addListenerToAddButton(productListPanel);
            addListenerToMinusButton(productListPanel);
        }
    }

    private void addListenerToMinusButton(final ProductListPanel productListPanel) {
        productListPanel.getMinusButton()
                .addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(final MouseEvent e) {
                        ProductListPanel panel = (ProductListPanel) ((NormalButton) e.getSource()).getParentComponent();
                        detailTableFrame.minusProduct(panel);
                    }
                });
    }

    private void addListenerToAddButton(final ProductListPanel productListPanel) {
        productListPanel.getAddButton()
                .addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(final MouseEvent e) {
                        ProductListPanel panel = (ProductListPanel) ((NormalButton) e.getSource()).getParentComponent();
                        detailTableFrame.putProduct(panel);
                    }
                });
    }
}
