package com.project.view.common;
import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.utils.TimeUtil;
import java.awt.Container;
import java.time.format.TextStyle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class LaunchPage extends JFrame {
    private static final LaunchPage instance = new LaunchPage();

    private final NormalButton startButton = new NormalButton(ButtonNameUtil.OPEN_POS);
    private final NormalButton endButton = new NormalButton(ButtonNameUtil.END_APPLICATION);
    private final Container container = getContentPane();
    private final JTextField jTextFieldTime=new JTextField(String.valueOf(TimeUtil.nowDateTime));
    public static LaunchPage getInstance() {
        return instance;
    }

    private LaunchPage() {
        initializePage();
        container.setLayout(null);
        container.add(startButton);
        container.add(endButton);
        container.add(jTextFieldTime);
        jTextFieldTime.setEditable(false);
        jTextFieldTime.setBounds(350,0,150,100);
        startButton.setBounds(350, 100, 150, 150);
        endButton.setBounds(350, 300, 150, 150);
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
