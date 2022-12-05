package com.project.view.sell;

import com.project.domain.SeatProduct;
import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.utils.TableNumberConstant;
import com.project.view.common.NormalButton;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SellingPanelTab extends JPanel {
    private static final SellingPanelTab instance = new SellingPanelTab();
    private final JPanel tablePanel = new JPanel();
    private final TableSubPanel[] tablePanels = new TableSubPanel[TableNumberConstant.NUMBER_OF_TABLE];
    private final NormalButton closeButton = new NormalButton(ButtonNameMessage.CLOSE_POS);
    private final NormalButton orderButton = new NormalButton(ButtonNameMessage.ORDER);
    private final NormalButton billButton = new NormalButton(ButtonNameMessage.BILL_CHECK);

    public static SellingPanelTab getInstance() {
        return instance;
    }

    public TableSubPanel[] getTablePanels() {
        return tablePanels;
    }

    private SellingPanelTab() {
        setLayout(null);
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        add(closeButton);
        add(billButton);
        add(orderButton);
        tablePanel.setLayout(new GridLayout(3, 3, 3, 3));

        for (int i = 0; i < TableNumberConstant.NUMBER_OF_TABLE; i++) {
            tablePanels[i] = new TableSubPanel(String.valueOf(i + 1), true);
            tablePanel.add(tablePanels[i]);
        }
        add(tablePanel);
        tablePanel.setBounds(0, 30, 650, 500);
        billButton.setBounds(650, 330, 150, 100);
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

    public void setSeatProductList(final List<SeatProduct> seatProducts, final int tableNumber) {
        tablePanels[tableNumber - 1].addSeatProductList(seatProducts);
    }
}
