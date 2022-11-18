package com.project.pages.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DetailTablePage extends JPanel {
    private static final DetailTablePage instance = new DetailTablePage();
    private final NormalButton detailTableBackButton = new NormalButton(ButtonNameUtil.BACK);
    private final NormalButton detailTableDiscountButton = new NormalButton(ButtonNameUtil.DISCOUNT);
    private final NormalButton detailTablePayButton = new NormalButton(ButtonNameUtil.PAYMENT);
    private final NormalButton detailTableOrderButton = new NormalButton(ButtonNameUtil.ORDER);
    private final NormalButton detailTableBillButton = new NormalButton(ButtonNameUtil.BILL_CHECK);
    private final JTextArea jTextAreaMenu = new JTextArea();
    private final JTextField jTextFieldTotal = new JTextField();
    private TablePanel tablePanel;
    private final JPanel ButtonPanelRight = new JPanel(new GridLayout(1, 2));
    private final JPanel ButtonPanelLeft = new JPanel(new GridLayout(1, 3));

    public static DetailTablePage getInstance() {
        return instance;
    }

    private DetailTablePage() {
        initializePage();
        ButtonPanelRight.add(detailTableOrderButton);
        ButtonPanelRight.add(detailTableBillButton);
        ButtonPanelLeft.add(detailTablePayButton);
        ButtonPanelLeft.add(detailTableDiscountButton);
        ButtonPanelLeft.add(detailTableBackButton);
        add(ButtonPanelRight);
        add(ButtonPanelLeft);
        ButtonPanelLeft.setBounds(50, 425, 300, 100);
        ButtonPanelRight.setBounds(550, 425, 200, 100);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
    }

    public void setTablePanel(TablePanel tablePanel) {
        this.tablePanel = new TablePanel(String.valueOf(tablePanel.getTableNumber()));
        JPanel test = new JPanel();
        test.setLayout(new BorderLayout());
        test.add(jTextFieldTotal, BorderLayout.SOUTH);
        test.add(jTextAreaMenu, BorderLayout.CENTER);
        this.tablePanel.add(test);
        jTextAreaMenu.setColumns(34);
        jTextAreaMenu.setRows(21);//jTextfieldTable 넣고 싶으면 20으로
        jTextAreaMenu.setEditable(false);
        jTextFieldTotal.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextAreaMenu);
        test.add(jScrollPane);
        add(this.tablePanel);
        this.tablePanel.setBounds(410, 0, 400, 400);
    }


    public JButton getBackButton() {
        return detailTableBackButton;
    }

    public void removeExistTablePanel() {
        remove(tablePanel);
    }

    public void clearjTextFieldTotal() {
        jTextFieldTotal.setText("");
    }

    public void clearjTextAreaMenu() {
        jTextAreaMenu.setText("");
    }
}
