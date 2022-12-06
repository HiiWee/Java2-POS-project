package com.project.view.common;

import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.utils.TimeUtil;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LaunchPage extends JFrame {
    private Image background = new ImageIcon("src/main/java/com/project/image/logo.png").getImage();
    private static final LaunchPage instance = new LaunchPage();
    private final NormalButton startButton = new NormalButton(ButtonNameMessage.OPEN_POS);
    private final NormalButton endButton = new NormalButton(ButtonNameMessage.END_APPLICATION);
    private final Container container = getContentPane();
    private final JTextField jTextFieldTime = new JTextField(String.valueOf(TimeUtil.nowDateTime));
    public static LaunchPage getInstance() {
        return instance;
    }
    private LaunchPage() {
        JPanel logo = new JPanel(){
            public void paint(Graphics g){
                g.drawImage(background, 0, 0, null);
            }
        };

        initializePage();
        container.setLayout(null);
        container.add(startButton);
        container.add(endButton);
        container.add(jTextFieldTime);
        container.setBackground(Color.WHITE);
        container.add(logo);
        logo.setBounds(325, 50, 200, 200);
        jTextFieldTime.setEditable(false);
        jTextFieldTime.setBounds(375, 300, 100, 50);
        jTextFieldTime.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldTime.setBackground(new Color(24,168, 241));
        jTextFieldTime.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        jTextFieldTime.setForeground(Color.WHITE);
        startButton.setBounds(250, 400, 150, 75);
        endButton.setBounds(450, 400, 150, 75);
    }

    private void initializePage() {
        setTitle("POS SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        setResizable(false);
    }


    public JButton getStartButton() {
        return startButton;
    }

    public JButton getEndButton() {
        return endButton;
    }
}
