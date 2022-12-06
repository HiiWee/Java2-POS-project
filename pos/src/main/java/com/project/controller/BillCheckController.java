package com.project.controller;

import com.project.controller.dto.SellProductInBillDto;
import com.project.service.BillCheckService;
import com.project.service.SellService;
import com.project.view.billcheck.BillCheckFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;

public class BillCheckController {
    public static final int TARGET_LISTENER_COUNT = 2;
    public static final int INIT_LISTENER_COUNT = 0;

    private final BillCheckFrame billCheckFrame = BillCheckFrame.getInstance();
    private final BillCheckService billCheckService = new BillCheckService();
    private final SellService sellService = new SellService();

    public void initBillCheckFrame() {
        billCheckFrame.addTotalSellProducts(billCheckService.findAllByTotalSellProduct());
        addListenerToTable();
        addListenerToRefundButton();
    }

    private void addListenerToRefundButton() {
        JButton refundButton = billCheckFrame.getRefundButton();
        if (refundButton.getActionListeners().length > INIT_LISTENER_COUNT) {
            return;
        }
        billCheckFrame.getRefundButton().addActionListener(e -> refund());
    }

    private void refund() {
        JTable billTable = billCheckFrame.getBillTable();
        long sellId = Long.parseLong((String) billTable.getValueAt(billTable.getSelectedRow(), 0));
        sellService.deleteBySellId(sellId);
        billCheckFrame.refund();
    }

    private void addListenerToTable() {
        JTable billTable = billCheckFrame.getBillTable();
        if (billTable.getMouseListeners().length > TARGET_LISTENER_COUNT) {
            return;
        }
        billTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                JTable detailTable = (JTable) e.getSource();
                List<SellProductInBillDto> sellProductInBillDtos = sellService.findAllProductById(
                        Long.parseLong((String) detailTable.getValueAt(detailTable.getSelectedRow(), 0)));
                billCheckFrame.clickBillCell(sellProductInBillDtos);
            }
        });
    }


}