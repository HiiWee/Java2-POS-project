package com.project.view.management;

import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManagementFrame extends JFrame {
    private static final ManagementFrame instance = new ManagementFrame();

    public static ManagementFrame getInstance() {
        return instance;
    }

    private final NormalButton backButtonOnManagementPage = new NormalButton(ButtonNameMessage.BACK);
    private final NormalButton addStuffButton = new NormalButton(ButtonNameMessage.ADD_STUFF);
    private final NormalButton deleteStuffButton = new NormalButton(ButtonNameMessage.DELETE_STUFF);
    private final NormalButton refreshButton = new NormalButton("상품갱신");
    private final String header[] = {"고유번호", "이름", "가격"};
    public DefaultTableModel tableModel;
    public JTable table;
    private final JPanel jPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

    public ManagementFrame() {
        initializePage();
        setTable();
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
        setVisible(false);
    }

    private void initializePage() {
        setLayout(null);
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Management Page");
    }

    private void setTable() {
        tableModel = new DefaultTableModel(header, 0) {
            public boolean isCellEditable(int row, int column) {
                return column < 0;
            }
        };
        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getDeleteButton() {
        return deleteStuffButton;
    }
}
