package com.project.view.management;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManagementPage extends JFrame {
    private static final ManagementPage instance = new ManagementPage();

    public static ManagementPage getInstance() {
        return instance;
    }

    private final NormalButton backButtonOnManagementPage = new NormalButton(ButtonNameUtil.BACK);
    private final NormalButton addStuffButton = new NormalButton(ButtonNameUtil.ADD_STUFF);
    private final NormalButton deleteStuffButton = new NormalButton(ButtonNameUtil.DELETE_STUFF);
    private final NormalButton refreshButton = new NormalButton("상품갱신");
    private final String header[] = {"고유번호", "이름", "가격"};
    public DefaultTableModel tableModel = new DefaultTableModel(header, 0);
    public JTable table = new JTable(tableModel);
    private final JPanel jPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

    public ManagementPage() {
        initializePage();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        jPanel.add(scrollPane);
        buttonPanel.add(refreshButton);
        buttonPanel.add(addStuffButton);
        buttonPanel.add(deleteStuffButton);
        add(buttonPanel);
        add(jPanel);
        add(backButtonOnManagementPage);
        backButtonOnManagementPage.setBounds(230, 480, 150, 100);
        jPanel.setBounds(50, 50, 500, 400);
        buttonPanel.setBounds(600, 100, 150, 300);
        setVisible(true);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Management Page");
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getDeleteButton() {
        return deleteStuffButton;
    }
}
