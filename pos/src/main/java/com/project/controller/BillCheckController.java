package com.project.controller;

import com.project.controller.dto.SellProductInBillDto;
import com.project.service.BillCheckService;
import com.project.service.SellService;
import com.project.view.billcheck.BillCheckFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;

public class BillCheckController {
    private final BillCheckFrame billCheckFrame = BillCheckFrame.getInstance();
    private final BillCheckService billCheckService = new BillCheckService();
    private final SellService sellService = new SellService();

    public void initBillCheckFrame() {
        billCheckFrame.addTotalSellProducts(billCheckService.findAllByTotalSellProduct());
        addListenerToTable();
    }

    private void addListenerToTable() {
        billCheckFrame.getBillTable().addMouseListener(new MouseAdapter() {
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