package com.project.view;

import com.project.view.management.ManagementPage;
import com.project.view.sales.SalesPage;
import com.project.view.sell.SellingPage;
import com.project.utils.InitializationGuiUtil;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
    private static final MainFrame instance = new MainFrame();
    private final SellingPage sellingPage = SellingPage.getInstance();
    private final SalesPage salesPage = SalesPage.getInstance();
    private final ManagementPage managementPage = ManagementPage.getInstance();
    private final JTabbedPane posTab = new JTabbedPane();

    public static MainFrame getInstance() {
        return instance;
    }

    public MainFrame() {
        setTitle("POS SYSTEM");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        posTab.setSize(InitializationGuiUtil.TAB_WIDTH, InitializationGuiUtil.TAB_HEIGHT);
        posTab.addTab("영업", sellingPage);
        posTab.addTab("판매", salesPage);
        posTab.addTab("관리", managementPage);
        add(posTab);

    }

    private void initializeTab() {
    }
}

