package com.project.view.sell;

import com.project.domain.SeatProduct;
import java.awt.Color;
import java.awt.FlowLayout;
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
            setTableColumnSize();
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setReorderingAllowed(false);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            JScrollPane jScrollPane = new JScrollPane(table);
            add(jScrollPane);
        }
    }

    private void setTableColumnSize() {
        table.getTableHeader().getColumnModel().getColumn(0).setWidth(130);
        table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(130);
        table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(130);
        table.getTableHeader().getColumnModel().getColumn(1).setWidth(80);
        table.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(80);
        table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(80);
        table.getTableHeader().getColumnModel().getColumn(2).setWidth(90);
        table.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(90);
        table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(90);
        table.getTableHeader().getColumnModel().getColumn(3).setWidth(40);
        table.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(40);
        table.getTableHeader().getColumnModel().getColumn(3).setMinWidth(40);
    }

    public long getTableNumber() {
        return Integer.parseInt(String.valueOf(tableNumber.getText().charAt(0)));
    }

    public void addSeatProductList(final List<SeatProduct> seatProducts) {
        System.out.println(seatProducts.size());
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
                String.valueOf(seatProduct.getSeatId()),
                seatProduct.getProductName(),
                String.valueOf(seatProduct.getPrice()),
                String.valueOf(seatProduct.getQuantity())
        });
    }
}
