package com.project.view.sell;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DetailTablePage extends JFrame {
    private static final DetailTablePage instance = new DetailTablePage();
    private final NormalButton DetailTableBackButton = new NormalButton(ButtonNameUtil.BACK);
    private ConstructorTablePanel tablePanel;
    private final Container container = getContentPane();

    public static DetailTablePage getInstance() {
        return instance;
    }

    private DetailTablePage() {
        initializePage();
        DetailTableBackButton.setBounds(500, 430, 150, 100);
        container.add(DetailTableBackButton);
    }

    private void initializePage() {
        setTitle("POS SYSTEM");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setResizable(false);
    }

    public void setTablePanel(ConstructorTablePanel constructorTablePanel) {
        tablePanel = new ConstructorTablePanel(String.valueOf(constructorTablePanel.getTableNumber()));
        tablePanel.setBounds(410, 0, 400, 400);
        container.add(tablePanel);
    }


    public JButton getBackButton() {
        return DetailTableBackButton;
    }

    public void removeExistTablePanel() {
        remove(tablePanel);
    }
}
