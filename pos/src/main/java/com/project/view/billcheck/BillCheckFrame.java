package com.project.view.billcheck;

import com.project.controller.dto.SellProductInBillDto;
import com.project.controller.dto.TotalSellProductDto;
import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BillCheckFrame extends JFrame {
    private static final BillCheckFrame instance = new BillCheckFrame();

    public static BillCheckFrame getInstance() {
        return instance;
    }

    private final NormalButton backButton = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton refundButton = new NormalButton(ButtonNameMessage.REFUND);
    private final JPanel panel = new JPanel();
    private JPanel detailBill;
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[]{"id", "판매일", "전체 가격", "총 수량"}, 0) {
        public boolean isCellEditable(int row, int column) {
            return column < 0;
        }
    };
    private final JTable billTable = new JTable(tableModel);

    private BillCheckFrame() {
        initializePage();
        initializePanel();
        JScrollPane jScrollPane = new JScrollPane(billTable);
        jScrollPane.setPreferredSize(new Dimension(450, 420));
        panel.add(jScrollPane);
        add(refundButton);
        add(backButton);
        backButton.setBounds(510, 450, 150, 100);
        refundButton.setBounds(660, 450, 150, 100);
    }

    public void clickBillCell(List<SellProductInBillDto> sellProductInBillDtos) {
        DefaultTableModel detailBillTableModel = createDetailBillTable();
        sellProductInBillDtos.forEach(sellProductInBillDto -> {
            detailBillTableModel.addRow(new String[]{
                    sellProductInBillDto.getProductName(),
                    String.valueOf(sellProductInBillDto.getQuantity()),
                    String.valueOf(sellProductInBillDto.getPrice())
            });
        });
    }

    private DefaultTableModel createDetailBillTable() {
        if (detailBill != null) {
            remove(detailBill);
        }
        detailBill = new JPanel();
        DefaultTableModel detailTableModel = new DefaultTableModel(new String[]{"상품명", "수량", "개당 가격"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return column < 0;
            }
        };
        JTable detailTable = new JTable(detailTableModel);
        detailTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane detailScroll = new JScrollPane(detailTable);
        detailScroll.setPreferredSize(new Dimension(250, 420));
        detailBill.add(detailScroll);
        detailBill.setBounds(500, 10, 300, 500);
        backButton.setVisible(false);
        backButton.setVisible(true);
        add(detailBill);
        return detailTableModel;
    }

    private void initializePanel() {
        panel.setBounds(10, 10, 500, 525);
        add(panel);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("POS SYSTEM");
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getRefundButton() {
        return refundButton;
    }

    public void addTotalSellProducts(final List<TotalSellProductDto> allByTotalSellProduct) {
        removeExistTableValue();
        allByTotalSellProduct.forEach(
                totalSellProductDto -> tableModel.addRow(new String[]{
                        String.valueOf(totalSellProductDto.getSellId()),
                        String.valueOf(totalSellProductDto.getLocalDate()),
                        String.valueOf(totalSellProductDto.getTotalPrice()),
                        String.valueOf(totalSellProductDto.getTotalQuantity())
                }));
    }

    private void removeExistTableValue() {
        for (int row = tableModel.getRowCount() - 1; row >= 0; row--) {
            tableModel.removeRow(row);
        }
    }

    public JTable getBillTable() {
        return billTable;
    }

    public void refund() {
        tableModel.removeRow(billTable.getSelectedRow());
        detailBill.setVisible(false);
        remove(detailBill);
    }
}
