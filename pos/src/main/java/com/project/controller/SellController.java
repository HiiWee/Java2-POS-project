package com.project.controller;

import com.project.domain.SeatProduct;
import com.project.service.SellService;
import com.project.utils.ButtonNameMessage;
import com.project.view.MainFrame;
import com.project.view.common.NormalButton;
import com.project.view.sell.DetailTableFrame;
import com.project.view.sell.SellingPanelTab;
import com.project.view.sell.listener.DetailTableFrameListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

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

    // 주문 버튼 클릭시 동작
    private void orderProduct() {
        sellService.saveSeatProducts(detailTableFrame.getSeatProductList(), detailTableFrame.getTableNumber());
        moveDetailToSellingPanelTab();
        SellingPanelTab.getInstance()
                .setSeatProductList(detailTableFrame.getSeatProductList(), (int) detailTableFrame.getTableNumber());
    }

    private void paySeatProduct() {
        sellService.paySeatProduct(detailTableFrame.getSeatProductList());
        detailTableFrame.setPriceLabel("총 가격");
        SellingPanelTab.getInstance()
                        .setSeatProductList(Collections.emptyList(), (int)detailTableFrame.getTableNumber());
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
