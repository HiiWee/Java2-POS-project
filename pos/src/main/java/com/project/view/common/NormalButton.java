package com.project.view.common;

import java.awt.Component;
import javax.swing.JButton;

public class NormalButton extends JButton {
    private Component parentComponent;

    public NormalButton(String buttonName) {
        setText(buttonName);
    }

    public Component getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(final Component parentComponent) {
        this.parentComponent = parentComponent;
    }
}
