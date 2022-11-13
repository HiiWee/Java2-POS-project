package com.project.view.sell;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // TODO 이부분 컨트롤러로 어떻게 빼낼지? 눌렀을때 새로운 상세 테이블 페이지 열려야함
                System.out.println("press");
            }
        });
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }

}
