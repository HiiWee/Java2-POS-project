package com.project.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TurnOffButton extends JButton {

    TurnOffButton(String name) {        //버튼을 입력하면 시스템을 종료하도록 액션설정
        this.setText(name);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

    }
}
