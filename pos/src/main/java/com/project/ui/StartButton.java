package com.project.ui;

import java.awt.event.*;
import javax.swing.*;

public class StartButton extends JButton {

    StartButton(String name) {
        this.setText(name);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGUI();
                setVisible(false);
            }

        });
    }
}
