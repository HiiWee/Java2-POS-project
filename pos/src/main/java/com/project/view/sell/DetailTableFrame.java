package com.project.view.sell;

import com.project.domain.Product;
import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class DetailTableFrame extends JFrame {
    private static final DetailTableFrame instance = new DetailTableFrame();
    public static final int ID_COLUMN = 0;
    public static final int COUNT_COLUMN = 3;

    private final NormalButton detailTableBackButton = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton detailTableDiscountButton = new NormalButton(ButtonNameMessage.DISCOUNT);
    private final NormalButton detailTablePayButton = new NormalButton(ButtonNameMessage.PAYMENT);
    private final NormalButton detailTableOrderButton = new NormalButton(ButtonNameMessage.ORDER);
    private TableSubPanel tablePanel;
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[]{"id", "이름", "개당 가격", "수량"}, 0) {
        public boolean isCellEditable(int row, int column) {
            return column < 0;
        }
    };
    private final JTable menuSelectTable = new JTable(tableModel);
    private final JPanel ButtonPanelLeft = new JPanel(new GridLayout(1, 3));

    // 메뉴 이동 버튼
    private final NormalButton leftButton = new NormalButton("<");
    private final NormalButton rightButton = new NormalButton(">");
    private JPanel menuPanel;
    // 각 메뉴들
    private ProductListPanel[] productListPanels;
    private int startIndexOfProduct;

    public static DetailTableFrame getInstance() {
        return instance;
    }

    private DetailTableFrame() {
        initializePage();
        ButtonPanelLeft.add(detailTablePayButton);
        ButtonPanelLeft.add(detailTableDiscountButton);
        ButtonPanelLeft.add(detailTableBackButton);
        add(ButtonPanelLeft);
        add(detailTableOrderButton);
        ButtonPanelLeft.setBounds(50, 430, 300, 100);
        detailTableOrderButton.setBounds(550, 425, 150, 100);
    }

    private void initializePage() {
        setLayout(null);
        setMenuPage();
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setMenuPage() {
        menuPanel = new JPanel(new FlowLayout());
        menuPanel.setBounds(20, 20, 450, 550);
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(menuPanel);
        productListPanels = new ProductListPanel[16];
        for (int i = 0; i < 16; i++) {
            productListPanels[i] = new ProductListPanel();
            productListPanels[i].setPreferredSize(new Dimension(105, 85));
            productListPanels[i].setBorder(new LineBorder(Color.BLACK, 1));
            productListPanels[i].setBackground(Color.WHITE);
            menuPanel.add(productListPanels[i]);
        }
        leftButton.setBounds(10, 355, 100, 40);
        rightButton.setBounds(290, 355, 100, 40);
        menuPanel.add(leftButton);
        menuPanel.add(rightButton);
    }

    public void setTablePanel(TableSubPanel tablePanel) {
        this.tablePanel = new TableSubPanel(tablePanel.getTableNumber() + "번 테이블");
        menuSelectTable.getTableHeader().setReorderingAllowed(false);
        menuSelectTable.getTableHeader().setReorderingAllowed(false);
        menuSelectTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane jScrollPane = new JScrollPane(menuSelectTable);
        this.tablePanel.add(jScrollPane);
        add(this.tablePanel);
        this.tablePanel.setBounds(480, 20, 360, 350);
    }

    public JButton getBackButton() {
        return detailTableBackButton;
    }

    public void removeExistTablePanel() {
        remove(tablePanel);
    }

    // DB 관련 메소드 모음
    public void initProduct(final List<Product> products) {
        int productsSize = 16;
        if (products.size() < 16) {
            productsSize = products.size();
        }
        for (int i = startIndexOfProduct; i < productsSize; i++) {
            productListPanels[i].setProduct(products.get(i));
        }
    }

    public ProductListPanel[] getProductListPanels() {
        return productListPanels;
    }

    // 테이블에 메뉴 추가
    public void putProduct(final ProductListPanel panel) {
        long putId = Long.parseLong(panel.getIdText());
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Long id = Long.parseLong((String) tableModel.getValueAt(row, ID_COLUMN));
            if (id == putId) {
                int productCount = Integer.parseInt((String) tableModel.getValueAt(row, COUNT_COLUMN)) + 1;
                tableModel.setValueAt(String.valueOf(productCount), row, COUNT_COLUMN);
                return;
            }
        }
        tableModel.addRow(
                new String[]{panel.getIdText(), panel.getNameText(), panel.getPriceText(), String.valueOf(1)});
    }

    // 테이블에 메뉴 제거
    public void minusProduct(final ProductListPanel panel) {
        long putId = Long.parseLong(panel.getIdText());
        int productCount = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Long id = Long.parseLong((String) tableModel.getValueAt(row, ID_COLUMN));
            if (id == putId) {
                productCount = Math.max(Integer.parseInt((String) tableModel.getValueAt(row, COUNT_COLUMN)) - 1,
                        productCount);
                tableModel.setValueAt(String.valueOf(productCount), row, COUNT_COLUMN);
                deleteZeroCountRow(productCount, row);
                return;
            }
        }
    }

    private void deleteZeroCountRow(final int productCount, final int row) {
        if (productCount == 0) {
            tableModel.removeRow(row);
        }
    }
}