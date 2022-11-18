package com.project.pages;

import com.project.pages.sell.SellingPage;
import com.project.utils.InitializationGuiUtil;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainPage extends JFrame {
    private static final MainPage instance = new MainPage();
    private final SellingPage sellingPage = SellingPage.getInstance();
    private final JTabbedPane posTab = new JTabbedPane();

    public static MainPage getInstance() {
        return instance;
    }

    public MainPage() {
        setTitle("POS SYSTEM");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        posTab.setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        posTab.addTab("영업", sellingPage);
        add(posTab);

    }

    private void initializeTab() {
    }
}

