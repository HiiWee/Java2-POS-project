package com.project.view.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SellingPage extends JFrame {
    private static final SellingPage instance = new SellingPage();

    private JPanel buttonPanel;
    private JPanel tablePanel;
    private TablePanel[] tablePanels = new TablePanel[9];

    private final Container container = getContentPane();
    private final NormalButton closeButton = new NormalButton(ButtonNameUtil.CLOSE_POS);
    private final NormalButton orderButton = new NormalButton(ButtonNameUtil.ORDER);
    private final NormalButton billButton = new NormalButton(
            ButtonNameUtil.BILL_CHECK);

    public static SellingPage getInstance() {
        return instance;
    }

    public TablePanel[] getTablePanels() {
        return tablePanels;
    }

    private SellingPage() {
        initializePage();
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.add(closeButton);
        buttonPanel.add(billButton);
        buttonPanel.add(orderButton);
        tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(3, 3, 3, 3));// 어떻게 하면 생성자로 빼서 수정이 쉬워질까?

        for (int i = 0; i < 9; i++) {
            tablePanels[i] = new TablePanel(String.valueOf(i + 1));
            tablePanel.add(tablePanels[i]);
        }
        container.add(tablePanel);
        container.add(buttonPanel);
        tablePanel.setBounds(0, 30, 500, 500);
        billButton.setBounds(500, 430, 150, 100);
        orderButton.setBounds(650, 430, 150, 100);
        closeButton.setBounds(650, 30, 150, 100);
    }

    private void initializePage() {
        setTitle("POS SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setResizable(false);
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public JButton getOrderButton() {
        return orderButton;
    }

    public JButton getBillButton() {
        return billButton;
    }

}
