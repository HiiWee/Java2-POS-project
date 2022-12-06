package com.project.view.sales;

import com.project.utils.InitializationGuiConstant;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SalesPage extends JPanel {
    private static final SalesPage instance = new SalesPage();
    public static SalesPage getInstance() {
        return instance;
    }
    private final JTabbedPane salesTab = new JTabbedPane();
    DailySalesVolume dailySalesVolume = DailySalesVolume.getInstance();
    MonthlySales monthlySales = MonthlySales.getInstance();
    SalesVolumeByItem salesVolumeByItem = SalesVolumeByItem.getInstance();

    private SalesPage() {
        setLayout(null);
        salesTab.setSize(InitializationGuiConstant.TAB_WIDTH, InitializationGuiConstant.TAB_HEIGHT);
        salesTab.addTab("일일판매량", dailySalesVolume);
        salesTab.addTab("월별판매량", monthlySales);
        salesTab.addTab("품목별판매량", salesVolumeByItem);
        add(salesTab);
        setVisible(true);
    }

}
