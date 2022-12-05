package com.project.view.management;

import com.project.controller.ManagementController;
import com.project.domain.Product;
import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
    private final String header[] = {"고유번호", "이름", "가격"};
    private DefaultTableModel tableModel;
    public JTable table;
    private final JPanel jPanel = new JPanel();
    private final JPanel buttonPanel=new JPanel(new GridLayout(2,1));

    public ManagementFrame() {
        initializePage();
        setTable();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        jPanel.add(scrollPane);
        buttonPanel.add(addStuffButton);
        buttonPanel.add(backButtonOnManagementPage);
        add(jPanel);
        add(buttonPanel);
        jPanel.setBounds(50, 50, 500, 500);
        buttonPanel.setBounds(600, 125, 150, 250);
        setVisible(true);
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

    public void addRowTable(List<Product> products) {
        for (Product i : products) {
            tableModel.addRow(new Object[]{
                    i.getId(), i.getName(), i.getPrice()
            });
        }
    }
}
