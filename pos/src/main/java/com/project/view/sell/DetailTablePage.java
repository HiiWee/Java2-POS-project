package com.project.view.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DetailTablePage extends JFrame {
    private static final DetailTablePage instance = new DetailTablePage();
    private final NormalButton detailTableBackButton = new NormalButton(ButtonNameUtil.BACK);
    private final JTextArea jTextArea = new JTextArea("test");
    private final JTextField jTextField = new JTextField(ButtonNameUtil.TABLE_NUMBER);
    private final JTextField jTextField1 = new JTextField("합계");
    private TablePanel tablePanel;
    private final Container container = getContentPane();

    public static DetailTablePage getInstance() {
        return instance;
    }

    private DetailTablePage() {
        initializePage();
        detailTableBackButton.setBounds(500, 430, 150, 100);
        container.add(detailTableBackButton);

    }

    private void initializePage() {
        setTitle("POS SYSTEM");
        container.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
    }

    public void setTablePanel(TablePanel tablePanel) {
        this.tablePanel = new TablePanel(String.valueOf(tablePanel.getTableNumber()));
        jTextField1.setText(String.valueOf(tablePanel.getTableNumber()));
        this.tablePanel.setBackground(Color.gray);
        JPanel test = new JPanel();
        test.setBounds(50, 50, 200, 200);
        test.setBackground(Color.YELLOW);
        test.add(jTextField);
        test.add(jTextArea);
        test.add(jTextField1);
        this.tablePanel.add(test);
        jTextField.setColumns(10);
        jTextField.setLocation(100, 10);
        jTextArea.setColumns(10);
        jTextArea.setRows(20);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        test.add(jScrollPane);
//        jTextArea.setBounds(500, 100, 200, 200);
        container.add(this.tablePanel);
        this.tablePanel.setBounds(410, 0, 400, 400);
    }


    public JButton getBackButton() {
        return detailTableBackButton;
    }

    public void removeExistTablePanel() {
        remove(tablePanel);
    }
}
