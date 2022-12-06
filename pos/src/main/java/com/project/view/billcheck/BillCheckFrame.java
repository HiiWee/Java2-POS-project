package com.project.view.billcheck;

import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BillCheckFrame extends JFrame {
    private static final BillCheckFrame instance = new BillCheckFrame();
    private final Container ct = getContentPane();
    public static BillCheckFrame getInstance() {
        return instance;
    }

    private final NormalButton billCheckPageBackButton = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton billCheckPageRefundButton = new NormalButton(ButtonNameMessage.REFUND);
    private final JPanel panel = new JPanel();
    private final DefaultTableModel tableModel = new DefaultTableModel(new String[]{"id", "판매일", "전체 가격", "총 수량"}, 0) {
        public boolean isCellEditable(int row, int column) {
            return column < 0;
        }
    };
    private final JTable billTable = new JTable(tableModel);

    public BillCheckFrame() {
        initializePage();
        initializePanel();
        JScrollPane jScrollPane = new JScrollPane(billTable);
        panel.add(jScrollPane);
        add(billCheckPageRefundButton);
        add(billCheckPageBackButton);
        billCheckPageBackButton.setBounds(520, 435, 150, 100);
        billCheckPageRefundButton.setBounds(670, 435, 150, 100);
    }

    private void initializePanel() {
        panel.setBounds(10, 10, 500, 525);
        panel.setBackground(Color.WHITE);
        add(panel);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("POS SYSTEM");
        ct.setBackground(Color.WHITE);
    }

    public JButton getBackButtonOnBillCheckPage() {
        return billCheckPageBackButton;
    }

    public JButton getRefundButtonOnBillCheckPage() {
        return billCheckPageRefundButton;
    }

}
