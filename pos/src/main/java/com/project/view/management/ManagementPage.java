package com.project.view.management;

import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagementPage extends JPanel {

    private static final ManagementPage instance = new ManagementPage();

    public static ManagementPage getInstance() {
        return instance;
    }
    private final NormalButton pressPassWardButton=new NormalButton("Passward입력");

    public ManagementPage() {
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        this.setLayout(null);
        add(pressPassWardButton);
        pressPassWardButton.setBounds(350,100,150,100);
        setVisible(true);
    }
    public JButton getPressPasswardButton(){
        return pressPassWardButton;
    }
}
