package com.project.view.sell;

import com.project.domain.SeatProduct;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TableSubPanel extends JPanel {
    private final JTextField tableNumber = new JTextField();
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[]{"id", "이름", "개당 가격", "수량"}, 0) {
        public boolean isCellEditable(int row, int column) {
            return column < 0;
        }
    };
    private final JTable table = new JTable(tableModel);

    public TableSubPanel(String name, boolean hasProductArea) {
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        tableNumber.setText(name);
        tableNumber.setEditable(false);
        add(tableNumber);
        if (hasProductArea) {
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setReorderingAllowed(false);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            JScrollPane jScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            jScrollPane.setPreferredSize(new Dimension(300, 120));
            add(jScrollPane);
        }
    }

    public long getTableNumber() {
        return Integer.parseInt(String.valueOf(tableNumber.getText().charAt(0)));
    }

    public void addSeatProductList(final List<SeatProduct> seatProducts) {
        removeExistSeatProducts();
        seatProducts.forEach(this::addSeatProduct);
    }

    private void removeExistSeatProducts() {
        for (int row = tableModel.getRowCount() - 1; row >= 0; row--) {
            tableModel.removeRow(row);
        }
    }

    public void addSeatProduct(SeatProduct seatProduct) {
        tableModel.addRow(new String[]{
                String.valueOf(seatProduct.getProductId()),
                seatProduct.getProductName(),
                String.valueOf(seatProduct.getPrice()),
                String.valueOf(seatProduct.getQuantity())
        });
    }

    // 현재 테이블에 있는 product 정보를 가져옴
    public List<SeatProduct> getSeatProductList() {
        List<SeatProduct> currentSeatProducts = new ArrayList<>();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            long productId = Long.parseLong((String) tableModel.getValueAt(row, 0));
            String productName = (String) tableModel.getValueAt(row, 1);
            int price = Integer.parseInt((String) tableModel.getValueAt(row, 2));
            long quantity = Long.parseLong((String) tableModel.getValueAt(row, 3));
            currentSeatProducts.add(new SeatProduct(quantity, price, productId, productName, getTableNumber()));
        }
        return currentSeatProducts;
    }
}
