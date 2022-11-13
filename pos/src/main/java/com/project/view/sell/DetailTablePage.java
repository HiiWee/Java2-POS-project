package com.project.view.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DetailTablePage extends JFrame {
    private static final DetailTablePage instance = new DetailTablePage();
    private final NormalButton normalButton = new NormalButton(ButtonNameUtil.BACK);
    private TablePanel tablePanel;

    public static DetailTablePage getInstance() {
        return instance;
    }

    private DetailTablePage() {
        initializePage();
    }

    private void initializePage() {
        setTitle("POS SYSTEM");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setResizable(false);
        normalButton.setBounds(500, 430, 150, 100);
        add(normalButton);
    }

    public void setTablePanel(TablePanel tablePanel) {
        this.tablePanel = new TablePanel(String.valueOf(tablePanel.getTableNumber()));
        this.tablePanel.setBounds(410, 0, 400, 400);
        add(this.tablePanel);
    }


    public JButton getBackButton() {
        return normalButton;
    }

    public void removeExistTablePanel() {
        remove(tablePanel);
    }
}
