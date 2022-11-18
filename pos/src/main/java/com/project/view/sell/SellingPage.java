package com.project.view.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.utils.TableNumberUtil;
import com.project.view.common.NormalButton;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SellingPage extends JPanel {
    private static final SellingPage instance = new SellingPage();
    private final JPanel tablePanel = new JPanel();
    private final TableSubPanel[] tablePanels = new TableSubPanel[TableNumberUtil.NUMBER_OF_TABLE];
    private final NormalButton closeButton = new NormalButton(ButtonNameUtil.CLOSE_POS);
    private final NormalButton orderButton = new NormalButton(ButtonNameUtil.ORDER);
    private final NormalButton billButton = new NormalButton(ButtonNameUtil.BILL_CHECK);

    public static SellingPage getInstance() {
        return instance;
    }

    public TableSubPanel[] getTablePanels() {
        return tablePanels;
    }

    private SellingPage() {
        setLayout(null);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        add(closeButton);
        add(billButton);
        add(orderButton);
        tablePanel.setLayout(new GridLayout(3, 3, 3, 3));

        for (int i = 0; i < TableNumberUtil.NUMBER_OF_TABLE; i++) {
            tablePanels[i] = new TableSubPanel(String.valueOf(i + 1));
            tablePanel.add(tablePanels[i]);
        }
        add(tablePanel);
        tablePanel.setBounds(0, 30, 500, 500);
        billButton.setBounds(500, 430, 150, 100);
        orderButton.setBounds(650, 430, 150, 100);
        closeButton.setBounds(650, 30, 150, 100);
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
