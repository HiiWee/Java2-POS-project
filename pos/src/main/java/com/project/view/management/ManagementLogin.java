package com.project.view.management;

import com.project.utils.ButtonNameUtil;
import com.project.utils.InitializationGuiUtil;
import com.project.view.common.NormalButton;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ManagementLogin extends JFrame {
    private final JLabel passward = new JLabel(ButtonNameUtil.PASSWORD);
    private final NormalButton loginButton = new NormalButton(ButtonNameUtil.LOGIN);
    private final NormalButton backButtonOnManagementLogin = new NormalButton(ButtonNameUtil.BACK);
    private final JPasswordField pressPassward = new JPasswordField();
    private final JPanel JButtonPanel = new JPanel(new GridLayout(1, 2));
    private static final ManagementLogin instance = new ManagementLogin();

    public static ManagementLogin getInstance() {
        return instance;
    }

    public ManagementLogin() {
        initializePage();
        add(passward);
        add(pressPassward);
        JButtonPanel.add(loginButton);
        JButtonPanel.add(backButtonOnManagementLogin);
        add(JButtonPanel);
        JButtonPanel.setBounds(40, 50, 200, 50);
        passward.setBounds(20, 10, 50, 50);
        pressPassward.setBounds(90, 20, 150, 30);
    }

    private void initializePage() {
        setTitle("MANAGER LOGIN");
        setSize(InitializationGuiUtil.LOGIN_WIDTH,InitializationGuiUtil.LOGIN_HEIGHT);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getBackButtonOnManagementLogin() {
        return backButtonOnManagementLogin;
    }

    public void clearJPasswordField() {
        pressPassward.setText("");
    }
}
