package com.project.view.management;

import com.project.domain.Product;
import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManagementFrame extends JFrame {
    private static final ManagementFrame instance = new ManagementFrame();

    public static ManagementFrame getInstance() {
        return instance;
    }

    private final NormalButton backButton = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton addStuffButton = new NormalButton(ButtonNameMessage.ITEM_ADD);
    private final String header[] = {
            ButtonNameMessage.ITEM_NUMBER,
            ButtonNameMessage.ITEM_NAME,
            ButtonNameMessage.ITEM_PRICE};
    private DefaultTableModel tableModel;
    private final NormalButton changePasswordButton = new NormalButton(ButtonNameMessage.CHANGE);

    private JTable table;
    private final JPanel jPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

    private ManagementFrame() {
        initializePage();
        setTable();
        setLocationRelativeTo(null);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        jPanel.add(scrollPane);
        buttonPanel.add(addStuffButton);
        buttonPanel.add(backButton);
        buttonPanel.add(changePasswordButton);
        add(jPanel);
        add(buttonPanel);
        jPanel.setBounds(50, 50, 500, 500);
        buttonPanel.setBounds(600, 125, 150, 350);
    }

    public JTable getTable() {
        return table;
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Management Page");
    }

    private void setTable() {
        tableModel = new DefaultTableModel(header, 0) {
            public boolean isCellEditable(int row, int column) {
                return column < 0;
            }
        };
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    public void addRowTable(List<Product> products) {
        for (Product product : products) {
            tableModel.addRow(new Object[]{
                    product.getId(), product.getName(), product.getPrice()
            });
        }
    }

    public NormalButton getAddStuffButton() {
        return addStuffButton;
    }

    public NormalButton getBackButton() {
        return backButton;

    }

    public Product getProduct(final Product editedProduct) {
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            long id = (long) tableModel.getValueAt(row, 0);
            String name = ((String) tableModel.getValueAt(row, 1));
            int price = (int) tableModel.getValueAt(row, 2);
            if (editedProduct.getId() == id) {
                return new Product(id, name, price);
            }
        }
        return null;
    }

    public NormalButton getChangePasswordButton() {
        return changePasswordButton;
    }
}
