package com.project.view.management;

import com.project.utils.ButtonNameMessage;
import com.project.utils.InitializationGuiConstant;
import com.project.view.common.NormalButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ManagementEnterPanel extends JPanel {
    private static final ManagementEnterPanel instance = new ManagementEnterPanel();
    private final JPasswordField passwordField = new JPasswordField();
    private final JLabel info = new JLabel("관리자 모드로 진입하기 위해서는 비밀번호가 필요합니다");
    private final NormalButton loginButton = new NormalButton(ButtonNameMessage.LOGIN);
    private final NormalButton changePasswordButton=new NormalButton(ButtonNameMessage.CHANGE_PASSWORD);

    public static ManagementEnterPanel getInstance() {
        return instance;
    }

    public ManagementEnterPanel() {
        setSize(InitializationGuiConstant.FRAME_WIDTH, InitializationGuiConstant.FRAME_HEIGHT);
        this.setLayout(null);
        add(passwordField);
        add(info);
        add(loginButton);
        add(changePasswordButton);
        info.setBounds(250, 50, 350, 100);
        passwordField.setBounds(300, 150, 200, 30);
        loginButton.setBounds(300, 200, 200, 50);
        changePasswordButton.setBounds(325,400,150,75);
        setVisible(true);
    }

    public NormalButton getLoginButton() {
        return loginButton;
    }
}
