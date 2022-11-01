package com.project.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class StartGUI1 extends JFrame {
    StartGUI1() {
        super("음식점 포스기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container ct = getContentPane();
        setSize(1500, 1200);
        CalenderButton calender = new CalenderButton();                //날짜 선택 패널 생성
        TurnOffButton tf = new TurnOffButton("프로그램 종료");            //프로그램 종료 버튼 생성
        JButton st = new JButton("영업 개시");                            //이 부분 영업개시 버튼 클래스를 따로 만들어서 하려고 했는데 버튼 누르는 액션을 받은 후에
        st.addActionListener(new ActionListener() {                    //첫번째 창을 숨기고 두번째 창을 띄어야 하는데 첫번쨰 창을 숨길수 없어서 StartGUI클래스에서 작성함.
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainGUI();                                        //startGUI 프레임을 숨기는데 성공하면 MainGUI 프레임을 생성
                setVisible(false);
            }

        });
        ct.add(calender);
        ct.add(tf);
        ct.add(st);
        setVisible(true);
        ct.setLayout(null);
        calender.setBounds(500, 350, 500, 50);
        tf.setBounds(500, 900, 500, 200);
        st.setBounds(500, 400, 500, 200);
        setResizable(false);

    }

}


public class StartGUI {

    public static void main(String[] args) {
        new StartGUI1();
    }

}
