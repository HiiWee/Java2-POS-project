package com.project.view.common;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LaunchPage extends JFrame {
    private final NormalButton startButton = new NormalButton(ButtonNameUtil.OPEN_POS);
    private final NormalButton endButton = new NormalButton(ButtonNameUtil.END_APPLICATION);
    private final Container container = getContentPane();

    public LaunchPage() {
        initializePage();
        container.setLayout(null);
        container.add(startButton);
        container.add(endButton);
        startButton.setBounds(350, 100, 150, 150);
        endButton.setBounds(350, 300, 150, 150);
        setVisible(true);
    }

    private void initializePage() {
        setTitle("POS SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        setResizable(false);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getEndButton() {
        return endButton;
    }
}
