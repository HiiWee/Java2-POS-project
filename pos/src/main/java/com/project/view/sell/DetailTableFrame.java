package com.project.view.sell;

import com.project.domain.Product;
import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import com.project.view.sell.listener.DetailTableFrameListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    public static final int PRICE_COLUMN = 2;

    private final NormalButton backButton = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton discountButton = new NormalButton(ButtonNameMessage.DISCOUNT);
    private final NormalButton payButton = new NormalButton(ButtonNameMessage.PAYMENT);
    private final NormalButton orderButton = new NormalButton(ButtonNameMessage.ORDER);
    private TableSubPanel tablePanel;
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[]{"id", "이름", "개당 가격", "수량"}, 0) {
        public boolean isCellEditable(int row, int column) {
            return column < 0;
        }
    };
    private final JTable menuSelectTable = new JTable(tableModel);
    private final JPanel buttonPanelLeft = new JPanel(new GridLayout(1, 3));

    // 메뉴 이동 버튼
    private final NormalButton leftButton = new NormalButton("<");
    private final NormalButton rightButton = new NormalButton(">");
    private JPanel menuPanel;
    // 각 메뉴들
    private ProductListPanel[] productListPanels;
    // 페이징시 사용되는 index
    private int startIndexOfProduct;
    private int productsSize = 16;
    private List<Product> products;
    private final JLabel totalPriceLabel = new JLabel();

    public static DetailTableFrame getInstance() {
        return instance;
    }

    private DetailTableFrame() {
        initializePage();
        initializeActionCommandOnBUtton();
        add(buttonPanelLeft);
        add(orderButton);
        add(totalPriceLabel);
        buttonPanelLeft.add(payButton);
        buttonPanelLeft.add(discountButton);
        buttonPanelLeft.add(backButton);
        buttonPanelLeft.setBounds(50, 430, 300, 100);
        orderButton.setBounds(550, 425, 150, 100);
        totalPriceLabel.setText("총 가격");
        totalPriceLabel.setBounds(480, 380, 360, 20);
        totalPriceLabel.setOpaque(true);
        totalPriceLabel.setBackground(Color.WHITE);
    }

    private void initializeActionCommandOnBUtton() {
        orderButton.setActionCommand(ButtonNameMessage.ORDER);
        discountButton.setActionCommand(ButtonNameMessage.DISCOUNT);
        payButton.setActionCommand(ButtonNameMessage.PAYMENT);
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
        return backButton;
    }

    public void removeExistTablePanel() {
        remove(tablePanel);
    }

    public ProductListPanel[] getProductListPanels() {
        return productListPanels;
    }

    public NormalButton getOrderButton() {
        return orderButton;
    }

    public NormalButton getPayButton() {
        return payButton;
    }

    public NormalButton getDiscountButton() {
        return payButton;
    }

    public NormalButton getLeftButton() {
        return leftButton;
    }

    public NormalButton getRightButton() {
        return rightButton;
    }

    /**
     * <, > 선택시 메뉴 다음 페이지 이동
     */
    public void movePrevPage() {
        if (startIndexOfProduct > 0) {
            startIndexOfProduct--;
            productsSize -= 16;
        }
        reInitProduct();
    }

    public void moveNextPage() {
        if (productsSize % 16 == 0) {
            startIndexOfProduct++;
            productsSize += 16;
            reInitProduct();
        }
    }

    /**
     * 데이터 삽입 관련 메소드
     */
    public void initProduct(final List<Product> products) {
        clearProduct();
        this.products = products;
        productsSize = 16;
        startIndexOfProduct = 0;
        if (products.size() < 16 * (startIndexOfProduct + 1)) {
            productsSize = products.size();
        }
        for (int i = startIndexOfProduct * 16; i < productsSize; i++) {
            productListPanels[i].setProduct(products.get(i));
        }
    }

    public void reInitProduct() {
        clearProduct();
        productsSize = 16 * (startIndexOfProduct + 1);
        if (products.size() < 16 * (startIndexOfProduct + 1)) {
            productsSize = products.size();
        }
        int index = 0;
        for (int i = startIndexOfProduct * 16; i < productsSize; i++) {
            productListPanels[index++].setProduct(products.get(i));
        }
    }

    private void clearProduct() {
        for (int i = 0; i < 16; i++) {
            productListPanels[i].clear();
        }
    }

    // 테이블에 메뉴 추가
    public void putProduct(final ProductListPanel panel) {
        long putId = Long.parseLong(panel.getIdText());
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Long id = Long.parseLong((String) tableModel.getValueAt(row, ID_COLUMN));
            if (id == putId) {
                int productCount = Integer.parseInt((String) tableModel.getValueAt(row, COUNT_COLUMN)) + 1;
                tableModel.setValueAt(String.valueOf(productCount), row, COUNT_COLUMN);
                updateTotalPrice();
                return;
            }
        }
        tableModel.addRow(
                new String[]{panel.getIdText(), panel.getNameText(), panel.getPriceText(), String.valueOf(1)});
        updateTotalPrice();
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
                updateTotalPrice();
                return;
            }
        }
        updateTotalPrice();
    }

    // 실시간 총 가격 업데이트
    private void updateTotalPrice() {
        int totalPrice = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            int count = Integer.parseInt((String) tableModel.getValueAt(row, COUNT_COLUMN));
            int eachPrice = Integer.parseInt((String) tableModel.getValueAt(row, PRICE_COLUMN));
            totalPrice += count * eachPrice;
        }
        totalPriceLabel.setText(totalPrice + " 원");
    }

    // 수량이 0개라면 테이블 row 삭제
    private void deleteZeroCountRow(final int productCount, final int row) {
        if (productCount == 0) {
            tableModel.removeRow(row);
        }
    }

}