package com.project.view.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;

public class NormalButton extends JButton {
    private Component parentComponent;
    public NormalButton(String buttonName) {setText(buttonName);
        setBackground(new Color(24,168, 241));
        setFont(new Font("맑은 고딕", Font.BOLD, 20));
        setForeground(Color.WHITE);
    }
    public Component getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(final Component parentComponent) {
        this.parentComponent = parentComponent;
    }
}
