package com.project.view.management;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ManagementSubPage extends JPanel {

    private static final ManagementSubPage instance = new ManagementSubPage();

    public static ManagementSubPage getInstance() {
        return instance;
    }
    private final JPasswordField passwordField = new JPasswordField();
    private final JLabel info = new JLabel("관리자 모드로 진입하기 위해서는 비밀번호가 필요합니다");
    private final NormalButton loginButton = new NormalButton(ButtonNameUtil.LOGIN);

    public ManagementSubPage() {
        setSize(InitializationGuiUtil.FRAME_WIDTH, InitializationGuiUtil.FRAME_HEIGHT);
        this.setLayout(null);
        add(passwordField);
        add(info);
        add(loginButton);
        info.setBounds(250,50,350,100);
        passwordField.setBounds(300,150,200,30);
        loginButton.setBounds(300,200,200,50);
        setVisible(true);
    }

}
