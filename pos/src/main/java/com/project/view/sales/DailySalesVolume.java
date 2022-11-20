package com.project.view.sales;

import javax.swing.JPanel;

public class DailySalesVolume extends JPanel {
    private static final DailySalesVolume instance = new DailySalesVolume();

    public static DailySalesVolume getInstance() {
        return instance;
    }

    public DailySalesVolume() {

    }
}
