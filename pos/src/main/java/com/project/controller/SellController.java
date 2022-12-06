package com.project.controller;

import com.project.service.SellService;
import com.project.utils.ButtonNameMessage;
import com.project.view.MainFrame;
import com.project.view.sell.DetailTableFrame;
import com.project.view.sell.SellingPanelTab;
import com.project.view.sell.TableSubPanel;
import com.project.view.sell.listener.DetailTableFrameListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import javax.swing.JOptionPane;

public class SellController implements ActionListener {
    private final SellService sellService = new SellService();
    private final DetailTableFrame detailTableFrame = DetailTableFrame.getInstance();
    private final DetailTableFrameListener detailTableFrameListener = new DetailTableFrameListener();

    /**
     * DB에서 현재 존재하는 메뉴 가져와서 심기
     */
    public void initSellPage() {
        detailTableFrame.initProduct(sellService.findAllProduct());
        detailTableFrameListener.addActionOnDetailTableFrameListener();
        setActionPerformed();
    }

    private void setActionPerformed() {
        detailTableFrame.getOrderButton().addActionListener(this);
        detailTableFrame.getPayButton().addActionListener(this);
    }

    public void refreshProducts(TableSubPanel currentTablePanel) {
        if (!sellService.checkAllProduct(currentTablePanel.getSeatProductList(), currentTablePanel.getTableNumber())) {
            JOptionPane.showMessageDialog(null, "판매하지 않는 물품이 존재합니다.", "alert", JOptionPane.PLAIN_MESSAGE);
            currentTablePanel.addSeatProductList(
                    sellService.findAllSeatProductById(currentTablePanel.getTableNumber()));
        }
        detailTableFrame.initProduct(sellService.findAllProduct());
    }


    // 주문 버튼 클릭시 동작
    private void orderProduct() {
        try {
            sellService.saveSeatProducts(detailTableFrame.getSeatProductList(), detailTableFrame.getTableNumber());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        moveDetailToSellingPanelTab();
        SellingPanelTab.getInstance()
                .setSeatProductList(detailTableFrame.getSeatProductList(), (int) detailTableFrame.getTableNumber());
    }

    private void paySeatProduct() {
        try {
            sellService.paySeatProduct(detailTableFrame.getSeatProductList());
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        detailTableFrame.setPriceLabel("총 가격");
        SellingPanelTab.getInstance()
                .setSeatProductList(Collections.emptyList(), (int) detailTableFrame.getTableNumber());
        sellService.clearSeatProducts(detailTableFrame.getTableNumber());
        moveDetailToSellingPanelTab();
    }

    private void moveDetailToSellingPanelTab() {
        detailTableFrame.setVisible(false);
        MainFrame.getInstance().setVisible(true);
        detailTableFrame.removeExistTablePanel();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals(ButtonNameMessage.ORDER)) {
            orderProduct();
        }
        if (actionCommand.equals(ButtonNameMessage.PAYMENT)) {
            paySeatProduct();
        }
    }
}
