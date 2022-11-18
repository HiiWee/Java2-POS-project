package com.project.view.sales;

import javax.swing.JPanel;

public class SalesPage extends JPanel {
    private static final SalesPage instance = new SalesPage();

    public static SalesPage getInstance() {
        return instance;
    }

    public SalesPage() {
        setVisible(true);
    }

}
