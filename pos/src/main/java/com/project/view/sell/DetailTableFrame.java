package com.project.view.sell;

import com.project.domain.Product;
import com.project.domain.SeatProduct;
import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.utils.TableNumberConstant;
import com.project.view.common.NormalButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
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
    public static final int NAME_COLUMN = 1;
    public static final int PRICE_COLUMN = 2;
    public static final int QUANTITY_COLUMN = 3;

    private final NormalButton backButton = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton payButton = new NormalButton(ButtonNameMessage.PAYMENT);
    private final NormalButton orderButton = new NormalButton(ButtonNameMessage.ORDER);
    private TableSubPanel tablePanel;
    private final DefaultTableModel[] tableModels = new DefaultTableModel[TableNumberConstant.NUMBER_OF_TABLE];
    private final JTable[] menuSelectTables = new JTable[TableNumberConstant.NUMBER_OF_TABLE];
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
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width / 2) - (this.getWidth() / 2), (dim.height / 2) - (this.getHeight() / 2));
        initializeFrame();
        initializeActionCommandOnButton();

        add(buttonPanelLeft);
        add(orderButton);
        add(totalPriceLabel);
        add(payButton);
        add(backButton);
        backButton.setBounds(720, 510, 120, 50);
        payButton.setBounds(480, 510, 120, 50);
        orderButton.setBounds(600, 510, 120, 50);
        totalPriceLabel.setText("총 가격");
        totalPriceLabel.setBounds(480, 480, 360, 20);
        totalPriceLabel.setOpaque(true);
        totalPriceLabel.setBackground(Color.WHITE);
    }

    private void initializeActionCommandOnButton() {
        orderButton.setActionCommand(ButtonNameMessage.ORDER);
        payButton.setActionCommand(ButtonNameMessage.PAYMENT);
    }

    private void initializeFrame() {
        setLayout(null);
        setMenuPage();
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setMenuPage() {
        menuPanel = new JPanel(new FlowLayout());
        menuPanel.setBounds(20, 20, 450, 450);
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
        initializeSeatProductTable();
        this.tablePanel = new TableSubPanel(tablePanel.getTableNumber() + "번 테이블", false);
        initExistSeatProduct(tablePanel.getSeatProductList());
        menuSelectTables[getCurrentSeatIndex()].getTableHeader().setReorderingAllowed(false);
        menuSelectTables[getCurrentSeatIndex()].getTableHeader().setReorderingAllowed(false);
        menuSelectTables[getCurrentSeatIndex()].setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(menuSelectTables[getCurrentSeatIndex()]);
        this.tablePanel.add(jScrollPane);
        add(this.tablePanel);
        this.tablePanel.setBounds(480, 20, 360, 450);
    }

    private void initExistSeatProduct(final List<SeatProduct> seatProductList) {
        clearSeatProduct((int) tablePanel.getTableNumber());
        removeExistTablePanel();
        seatProductList.forEach(seatProduct ->
                tableModels[getCurrentSeatIndex()].addRow(new String[]{
                        String.valueOf(seatProduct.getProductId()),
                        seatProduct.getProductName(),
                        String.valueOf(seatProduct.getPrice()),
                        String.valueOf(seatProduct.getQuantity())}
                )
        );

    }

    private void clearSeatProduct(final int tableNumber) {
        for (int row = tableModels[tableNumber - 1].getRowCount() - 1; row >= 0; row--) {
            tableModels[tableNumber - 1].removeRow(row);
        }
    }

    private void initializeSeatProductTable() {
        if (tableModels[0] != null) {
            return;
        }
        for (int i = 0; i < 9; i++) {
            tableModels[i] = new DefaultTableModel(new String[]{"id", "이름", "개당 가격", "수량"}, 0) {
                public boolean isCellEditable(int row, int column) {
                    return column < 0;
                }
            };
            menuSelectTables[i] = new JTable(tableModels[i]);
        }
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

    public NormalButton getLeftButton() {
        return leftButton;
    }

    public NormalButton getRightButton() {
        return rightButton;
    }

    public long getTableNumber() {
        return tablePanel.getTableNumber();
    }

    public void setPriceLabel(final String totalPrice) {
        totalPriceLabel.setText(totalPrice);
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
     * 메뉴 데이터 삽입 관련 메소드
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
        for (int row = 0; row < tableModels[getCurrentSeatIndex()].getRowCount(); row++) {
            Long id = Long.parseLong((String) tableModels[getCurrentSeatIndex()].getValueAt(row, ID_COLUMN));
            if (id == putId) {
                int productCount =
                        Integer.parseInt((String) tableModels[getCurrentSeatIndex()].getValueAt(row, QUANTITY_COLUMN))
                                + 1;
                tableModels[getCurrentSeatIndex()].setValueAt(String.valueOf(productCount), row, QUANTITY_COLUMN);
                updateTotalPrice();
                return;
            }
        }
        tableModels[getCurrentSeatIndex()].addRow(
                new String[]{panel.getIdText(), panel.getNameText(), panel.getPriceText(), String.valueOf(1)});
        updateTotalPrice();
    }

    private int getCurrentSeatIndex() {
        return (int) tablePanel.getTableNumber() - 1;
    }

    // 테이블에 메뉴 제거
    public void minusProduct(final ProductListPanel panel) {
        long putId = Long.parseLong(panel.getIdText());
        int productCount = 0;
        for (int row = 0; row < tableModels[getCurrentSeatIndex()].getRowCount(); row++) {
            Long id = Long.parseLong((String) tableModels[getCurrentSeatIndex()].getValueAt(row, ID_COLUMN));
            if (id == putId) {
                productCount = Math.max(
                        Integer.parseInt((String) tableModels[getCurrentSeatIndex()].getValueAt(row, QUANTITY_COLUMN))
                                - 1, productCount);
                tableModels[getCurrentSeatIndex()].setValueAt(String.valueOf(productCount), row, QUANTITY_COLUMN);
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
        for (int row = 0; row < tableModels[getCurrentSeatIndex()].getRowCount(); row++) {
            int count = Integer.parseInt((String) tableModels[getCurrentSeatIndex()].getValueAt(row, QUANTITY_COLUMN));
            int eachPrice = Integer.parseInt((String) tableModels[getCurrentSeatIndex()].getValueAt(row, PRICE_COLUMN));
            totalPrice += count * eachPrice;
        }
        totalPriceLabel.setText(totalPrice + " 원");
    }

    // 수량이 0개라면 테이블 row 삭제
    private void deleteZeroCountRow(final int productCount, final int row) {
        if (productCount == 0) {
            tableModels[getCurrentSeatIndex()].removeRow(row);
        }
    }

    // 사용자가 선택한 메뉴에 대한 리스트 받기
    public List<SeatProduct> getSeatProductList() {
        List<SeatProduct> selectedProducts = new ArrayList<>();
        for (int row = 0; row < tableModels[getCurrentSeatIndex()].getRowCount(); row++) {
            long productId = Long.parseLong((String) tableModels[getCurrentSeatIndex()].getValueAt(row, ID_COLUMN));
            String productName = (String) tableModels[getCurrentSeatIndex()].getValueAt(row, NAME_COLUMN);
            int price = Integer.parseInt((String) tableModels[getCurrentSeatIndex()].getValueAt(row, PRICE_COLUMN));
            long quantity = Long.parseLong(
                    (String) tableModels[getCurrentSeatIndex()].getValueAt(row, QUANTITY_COLUMN));
            selectedProducts.add(new SeatProduct(quantity, price, productId, productName, tablePanel.getTableNumber()));
        }
        return selectedProducts;
    }

}