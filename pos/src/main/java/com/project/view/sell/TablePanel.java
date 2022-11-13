package com.project.view.sell;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class TablePanel extends JPanel {
    private final JTextField tableNumber = new JTextField();

    public TablePanel(String name) {
        setBackground(Color.WHITE);
        tableNumber.setText(name);
        tableNumber.setEditable(false);
        add(tableNumber);
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    public int getTableNumber() {
        return Integer.parseInt(tableNumber.getText());
    }
}
