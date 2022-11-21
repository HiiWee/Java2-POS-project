package com.project.view.sales;

import javax.swing.JPanel;

public class SalesVolumeByItem extends JPanel {
    private static final SalesVolumeByItem instance = new SalesVolumeByItem();

    public static SalesVolumeByItem getInstance() {
        return instance;
    }

    SalesVolumeByItem() {

    }
}
