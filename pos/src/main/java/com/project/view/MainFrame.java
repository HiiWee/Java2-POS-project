package com.project.view;

import com.project.view.management.ManagementEnterPanel;
import com.project.view.sales.SalesPage;
import com.project.view.sell.SellingPanelTab;
import com.project.utils.InitializationGuiConstant;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
    private static final MainFrame instance = new MainFrame();
    private final SellingPanelTab sellingPage = SellingPanelTab.getInstance();
    private final SalesPage salesPage = SalesPage.getInstance();
    private final ManagementEnterPanel managementEnterPanel = new ManagementEnterPanel();
    private final JTabbedPane posTab = new JTabbedPane();

    public static MainFrame getInstance() {
        return instance;
    }

    public MainFrame() {
        setTitle("POS SYSTEM");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        setResizable(false);
        posTab.setSize(InitializationGuiConstant.TAB_WIDTH, InitializationGuiConstant.TAB_HEIGHT);
        posTab.addTab("영업", sellingPage);
        posTab.addTab("판매", salesPage);
        posTab.addTab("관리", managementEnterPanel);
        add(posTab);

    }

}

